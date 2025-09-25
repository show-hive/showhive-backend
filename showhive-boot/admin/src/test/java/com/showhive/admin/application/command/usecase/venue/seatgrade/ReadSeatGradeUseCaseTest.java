package com.showhive.admin.application.command.usecase.venue.seatgrade;

import com.showhive.admin.application.command.dto.venue.SeatGradeDto;
import com.showhive.admin.application.command.usecase.BaseUseCaseTest;
import com.showhive.admin.interfaces.venue.dto.SeatGradeListResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ReadSeatGradeUseCaseTest extends BaseUseCaseTest {

    @Autowired
    private CreateSeatGradeUseCase createSeatGradeUseCase;

    @Autowired
    private ReadSeatGradeUseCase readSeatGradeUseCase;

    @DisplayName("전체 좌석 등급을 조회할 수 있다.")
    @Test
    void read_all_seat_grades() {
        // given
        createSeatGradeUseCase.handle(new SeatGradeDto("R"));
        createSeatGradeUseCase.handle(new SeatGradeDto("S"));
        createSeatGradeUseCase.handle(new SeatGradeDto("A"));
        int pageSize = 10;
        int lastGradeId = 0;
        String keyword = null;
        String direction = "desc";
        // when
        SeatGradeListResponse seatGradeListResponse = readSeatGradeUseCase.readAllSeatGrades(pageSize, lastGradeId, keyword, direction);
        // then
        assertThat(seatGradeListResponse.seatGrades()).hasSize(3);
    }

    @DisplayName("마지막 좌석 등급 아이디 이후 좌석 등급 목록을 조회할 수 있다.")
    @Test
    void read_seat_grades_after_last_seat_grade_id() {
        // given
        createSeatGradeUseCase.handle(new SeatGradeDto("R"));
        createSeatGradeUseCase.handle(new SeatGradeDto("S"));
        createSeatGradeUseCase.handle(new SeatGradeDto("A"));
        int pageSize = 10;
        int lastGradeId = 3;
        String keyword = null;
        String direction = "desc";
        // when
        SeatGradeListResponse seatGradeListResponse = readSeatGradeUseCase.readAllSeatGrades(pageSize, lastGradeId, keyword, direction);
        // then
        assertAll(
                () -> assertThat(seatGradeListResponse.seatGrades()).hasSize(2),
                () -> assertThat(seatGradeListResponse.seatGrades().getFirst().grade()).isEqualTo("S"),
                () -> assertThat(seatGradeListResponse.seatGrades().getFirst().id()).isEqualTo(2),
                () -> assertThat(seatGradeListResponse.seatGrades().getLast().grade()).isEqualTo("R"),
                () -> assertThat(seatGradeListResponse.seatGrades().getLast().id()).isEqualTo(1)
        );
    }

    @DisplayName("검색 키워드 조건에 맞는 좌석 등급 목록을 조회할 수 있다.")
    @Test
    void read_seat_grades_by_keyword() {
        // given
        createSeatGradeUseCase.handle(new SeatGradeDto("R"));
        createSeatGradeUseCase.handle(new SeatGradeDto("S"));
        createSeatGradeUseCase.handle(new SeatGradeDto("A"));
        int pageSize = 10;
        int lastGradeId = 0;
        String keyword = "a";
        String direction = "desc";
        // when
        SeatGradeListResponse seatGradeListResponse = readSeatGradeUseCase.readAllSeatGrades(pageSize, lastGradeId, keyword, direction);
        // then
        assertAll(
                () -> assertThat(seatGradeListResponse.seatGrades()).hasSize(1),
                () -> assertThat(seatGradeListResponse.seatGrades().getFirst().grade()).isEqualTo("A")
        );
    }
}
