package com.showhive.venue.repository.command;

import com.showhive.venue.entity.SeatGradeEntity;

public interface SeatGradeCommandRepository {

    void create(SeatGradeEntity seatGrade);

    boolean existsById(long seatGradeId);

    void deleteById(long seatGradeId);
}
