package com.showhive.admin.application.command.usecase.seatgrade.impl;

import com.showhive.admin.application.command.dto.SeatGradeDto;
import com.showhive.admin.application.command.usecase.seatgrade.CreateSeatGradeUseCase;
import com.showhive.venue.domain.SeatGrade;
import com.showhive.venue.repository.command.SeatGradeCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateSeatGradeUseCaseImpl implements CreateSeatGradeUseCase {
    private final SeatGradeCommandRepository commandRepository;

    @Override
    public void handle(SeatGradeDto commandDto) {
        SeatGrade seatGrade = SeatGrade.create(commandDto.grade());
        commandRepository.create(seatGrade);
    }
}
