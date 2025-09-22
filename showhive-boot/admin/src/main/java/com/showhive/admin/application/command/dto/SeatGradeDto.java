package com.showhive.admin.application.command.dto;

import com.showhive.admin.interfaces.performance.dto.SeatGradeRequest;
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
