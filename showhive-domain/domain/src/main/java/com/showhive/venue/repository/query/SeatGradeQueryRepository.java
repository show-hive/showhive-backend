package com.showhive.venue.repository.query;

import com.showhive.common.PageResult;
import com.showhive.venue.domain.SeatGrade;

import java.util.List;
import java.util.Optional;

public interface SeatGradeQueryRepository {

    PageResult<SeatGrade> getList(SeatGrade seatGrade);

    Optional<SeatGrade> findById(long seatGradeId);

    List<SeatGrade> findAll();
}
