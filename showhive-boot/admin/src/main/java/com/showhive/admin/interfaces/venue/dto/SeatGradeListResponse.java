package com.showhive.admin.interfaces.venue.dto;

import java.util.List;

public record SeatGradeListResponse(
        List<SeatGradeResponse> seatGrades,
        boolean loadable
) {
}
