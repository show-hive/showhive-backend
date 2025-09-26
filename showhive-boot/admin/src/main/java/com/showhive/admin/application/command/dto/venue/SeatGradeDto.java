package com.showhive.admin.application.command.dto.venue;

import com.showhive.admin.interfaces.venue.dto.SeatGradeRequest;
import lombok.Builder;

@Builder
public record SeatGradeDto(
        String grade
) {
    public static SeatGradeDto of(SeatGradeRequest request) {
        return SeatGradeDto.builder()
                .grade(request.name())
                .build();
    }
}
