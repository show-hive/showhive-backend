package com.showhive.venue.repository.command;

import com.showhive.venue.domain.SeatGrade;

public interface SeatGradeCommandRepository {

    void create(SeatGrade seatGrade);

    boolean existsById(long seatGradeId);

    void deleteById(long seatGradeId);
}
