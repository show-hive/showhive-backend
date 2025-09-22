package com.showhive.admin.application.command.usecase.seatgrade.impl;

import com.showhive.admin.application.command.usecase.seatgrade.ReadSeatGradeUseCase;
import com.showhive.admin.interfaces.performance.dto.SeatGradeListResponse;
import com.showhive.admin.interfaces.performance.dto.SeatGradeResponse;
import com.showhive.venue.domain.SeatGrade;
import com.showhive.venue.exception.SeatGradeErrorCode;
import com.showhive.venue.exception.SeatGradeException;
import com.showhive.venue.repository.query.SeatGradeQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadSeatGradeUseCaseImpl implements ReadSeatGradeUseCase {

    private final SeatGradeQueryRepository seatGradeQueryRepository;

    @Override
    @Transactional
    public SeatGradeListResponse readAllSeatGrades() {
        List<SeatGrade> seatGrades = seatGradeQueryRepository.findAll();
        return new SeatGradeListResponse(seatGrades.stream()
                .map(seatGrade -> new SeatGradeResponse(seatGrade.getId(), seatGrade.getGrade()))
                .toList());
    }

    @Override
    public SeatGradeResponse readSeatGrade(long seatGradeId) {
        SeatGrade seatGrade = seatGradeQueryRepository.findById(seatGradeId)
                .orElseThrow(() -> new SeatGradeException(SeatGradeErrorCode.SEAT_GRADE_NOT_FOUND));
        return new SeatGradeResponse(seatGrade.getId(), seatGrade.getGrade());
    }
}
