package com.showhive.admin.seat_grade;

import com.showhive.venue.domain.SeatGrade;
import com.showhive.venue.exception.SeatGradeException;
import com.showhive.venue.repository.command.SeatGradeCommandRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class SeatGradeRepositoryTest {

    @Autowired
    private SeatGradeCommandRepository seatGradeCommandRepository;

    @DisplayName("좌석 등급 생성 테스트")
    @Test
    void seat_grade_save_test() {
        String name = "VIP";
        SeatGrade seatGrade = SeatGrade.create(name);

        seatGradeCommandRepository.create(seatGrade);
    }

    @DisplayName("좌석 등급 생성 실패 테스트 - 등급 값 불충분")
    @Test
    void seat_grade_save_fail_test() {
        String name = "";

        Assertions.assertThrows(SeatGradeException.class, () -> SeatGrade.create(name));

    }
}
