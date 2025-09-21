package com.showhive.venue.repository.query;

import com.showhive.common.PageResult;
import com.showhive.venue.domain.SeatGrade;

public interface SeatGradeQueryRepository {
    PageResult<SeatGrade> getList(SeatGrade seatGrade);
}
