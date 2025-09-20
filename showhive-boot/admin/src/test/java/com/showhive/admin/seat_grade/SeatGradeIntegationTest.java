package com.showhive.admin.seat_grade;

import com.showhive.admin.application.command.dto.CreateSeatGradeDto;
import com.showhive.admin.application.command.usecase.CreateSeatGradeUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SeatGradeIntegationTest {

    @Autowired
    private CreateSeatGradeUseCase createSeatGradeUseCase;

    @Test
    void seat_grade_save_test() {
        CreateSeatGradeDto createSeatGradeDto =
                CreateSeatGradeDto.builder()
                .grade("S")
                .build();
        createSeatGradeUseCase.handle(createSeatGradeDto);
    }
}
