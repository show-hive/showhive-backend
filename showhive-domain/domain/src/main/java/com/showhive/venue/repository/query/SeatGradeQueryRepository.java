package com.showhive.venue.repository.query;

import com.showhive.common.CursorPage;
import com.showhive.venue.domain.Direction;
import com.showhive.venue.domain.SeatGrade;

import java.util.Optional;

public interface SeatGradeQueryRepository {

    Optional<SeatGrade> findById(long seatGradeId);

    CursorPage<SeatGrade> findSeatGradesBy(long lastGradeId, int pageSize,
                                           String keyword, Direction direction);
}
