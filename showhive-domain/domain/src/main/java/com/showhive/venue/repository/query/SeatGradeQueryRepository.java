package com.showhive.venue.repository.query;

import com.showhive.common.CursorPage;
import com.showhive.venue.entity.Direction;
import com.showhive.venue.entity.SeatGradeEntity;
import java.util.Optional;

public interface SeatGradeQueryRepository {

    Optional<SeatGradeEntity> findById(long seatGradeId);

    CursorPage<SeatGradeEntity> findSeatGradesBy(long lastGradeId, int pageSize,
                                                 String keyword, Direction direction);
}
