package com.showhive.admin.seat_grade;

import com.showhive.admin.application.command.dto.SeatGradeDto;
import com.showhive.admin.application.command.usecase.seatgrade.CreateSeatGradeUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SeatGradeIntegationTest {

    @Autowired
    private CreateSeatGradeUseCase createSeatGradeUseCase;

    @Test
    void seat_grade_save_test() {
        SeatGradeDto seatGradeDto =
                SeatGradeDto.builder()
                        .grade("S")
                        .build();
        createSeatGradeUseCase.handle(seatGradeDto);
    }
}
