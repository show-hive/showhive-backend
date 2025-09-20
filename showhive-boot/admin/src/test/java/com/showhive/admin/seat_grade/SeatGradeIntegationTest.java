package com.showhive.admin.seat_grade;

import com.showhive.admin.application.command.usecase.venue.CreateSeatGradeUseCase;
import com.showhive.venue.domain.SeatGrade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SeatGradeIntegationTest {

    @Autowired
    private CreateSeatGradeUseCase createSeatGradeUseCase;

    @Test
    void seat_grade_save_test() {
        String name = "R";

        SeatGrade seatGrade = SeatGrade.create(name);
        createSeatGradeUseCase.handle(seatGrade);
    }
}
