package com.showhive.admin.application.command.usecase.venue.seatgrade.impl;

import com.showhive.admin.application.command.usecase.venue.seatgrade.ReadSeatGradeUseCase;
import com.showhive.admin.interfaces.venue.dto.SeatGradeListResponse;
import com.showhive.admin.interfaces.venue.dto.SeatGradeResponse;
import com.showhive.common.CursorPage;
import com.showhive.venue.entity.Direction;
import com.showhive.venue.entity.SeatGrade;
import com.showhive.venue.exception.SeatGradeErrorCode;
import com.showhive.venue.exception.SeatGradeException;
import com.showhive.venue.repository.query.SeatGradeQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadSeatGradeUseCaseImpl implements ReadSeatGradeUseCase {

    private final SeatGradeQueryRepository seatGradeQueryRepository;

    @Override
    public SeatGradeListResponse readAllSeatGrades(int pageSize, long lastGradeId,
                                                   String keyword, String direction) {
        Direction validDirection = Direction.from(direction);

        if (lastGradeId == 0 && validDirection == Direction.DESC) {
            lastGradeId = Long.MAX_VALUE;
        }

        CursorPage<SeatGrade> seatGradeSlice = seatGradeQueryRepository.findSeatGradesBy(lastGradeId, pageSize,
                keyword, validDirection);
        List<SeatGrade> seatGrades = seatGradeSlice.contents();
        boolean loadable = seatGradeSlice.hasNext();

        return new SeatGradeListResponse(seatGrades.stream()
                .map(seatGrade -> new SeatGradeResponse(seatGrade.getId(), seatGrade.getGrade()))
                .toList(),
                loadable);
    }

    @Override
    public SeatGradeResponse readSeatGrade(long seatGradeId) {
        SeatGrade seatGrade = seatGradeQueryRepository.findById(seatGradeId)
                .orElseThrow(() -> new SeatGradeException(SeatGradeErrorCode.SEAT_GRADE_NOT_FOUND));
        return new SeatGradeResponse(seatGrade.getId(), seatGrade.getGrade());
    }
}
