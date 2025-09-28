package com.showhive.admin.interfaces.venue.dto;

import com.showhive.venue.domain.SeatGrade;

public record SeatGradeResponse(
        Long id,
        String grade
) {
    public SeatGradeResponse(SeatGrade seatGrade) {
        this(seatGrade.getId(), seatGrade.getGrade());
    }
}
