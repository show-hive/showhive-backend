package com.showhive.admin.application.command.dto;

import com.showhive.admin.interfaces.performance.dto.CreateSeatGradeRequest;
import lombok.Builder;

@Builder
public record ListSeatGradeDto(
        Long seatGradeId,
        String grade
) {
    public static ListSeatGradeDto of(CreateSeatGradeRequest request) {
        return ListSeatGradeDto.builder()
                .grade(request.name())
                .build();
    }
}
