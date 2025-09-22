package com.showhive.admin.application.command.usecase.seatgrade.impl;

import com.showhive.admin.application.command.dto.SeatGradeDto;
import com.showhive.admin.application.command.usecase.seatgrade.UpdateSeatGradeUseCase;
import com.showhive.venue.domain.SeatGrade;
import com.showhive.venue.exception.SeatGradeErrorCode;
import com.showhive.venue.exception.SeatGradeException;
import com.showhive.venue.repository.query.SeatGradeQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UpdateSeatGradeUseCaseImpl implements UpdateSeatGradeUseCase {

    private final SeatGradeQueryRepository seatGradeQueryRepository;

    @Override
    @Transactional
    public void update(long seatGradeId, SeatGradeDto commandDto) {
        SeatGrade seatGrade = seatGradeQueryRepository.findById(seatGradeId)
                .orElseThrow(() -> new SeatGradeException(SeatGradeErrorCode.SEAT_GRADE_NOT_FOUND));
        seatGrade.changeGrade(commandDto.grade());
    }
}
