package com.showhive.venue.repository.query;

import com.showhive.common.CursorPage;
import com.showhive.venue.domain.SeatGrade;

import java.util.Optional;

public interface SeatGradeQueryRepository {
    
    Optional<SeatGrade> findById(long seatGradeId);

    CursorPage<SeatGrade> findAllByLessThanId(long lastGradeId, int pageSize);
}
