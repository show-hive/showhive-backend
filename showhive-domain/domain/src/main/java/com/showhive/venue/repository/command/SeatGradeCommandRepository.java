package com.showhive.venue.repository.command;

import com.showhive.venue.entity.SeatGrade;

public interface SeatGradeCommandRepository {

    void create(SeatGrade seatGrade);

    boolean existsById(long seatGradeId);

    void deleteById(long seatGradeId);
}
