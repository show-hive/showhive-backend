package com.showhive.admin.interfaces.performance.dto;

import java.util.List;

public record SeatGradeListResponse(
        List<SeatGradeResponse> seatGrades
) {
}
