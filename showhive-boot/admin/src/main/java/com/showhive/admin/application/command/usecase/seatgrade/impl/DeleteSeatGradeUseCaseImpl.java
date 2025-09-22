package com.showhive.admin.application.command.usecase.seatgrade.impl;

import com.showhive.admin.application.command.usecase.seatgrade.DeleteSeatGradeUseCase;
import com.showhive.venue.exception.SeatGradeErrorCode;
import com.showhive.venue.exception.SeatGradeException;
import com.showhive.venue.repository.command.SeatGradeCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeleteSeatGradeUseCaseImpl implements DeleteSeatGradeUseCase {

    private final SeatGradeCommandRepository seatGradeCommandRepository;

    @Override
    @Transactional
    public void deleteSeatGrade(long seatGradeId) {
        if (!seatGradeCommandRepository.existsById(seatGradeId)) {
            throw new SeatGradeException(SeatGradeErrorCode.SEAT_GRADE_NOT_FOUND);
        }
        seatGradeCommandRepository.deleteById(seatGradeId);
    }
}
