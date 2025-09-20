package com.showhive.admin.application.command.dto;

import com.showhive.admin.interfaces.performance.dto.CreateSeatGradeRequest;
import lombok.Builder;

@Builder
public record CreateSeatGradeDto(
        String grade
) {
    public static CreateSeatGradeDto of(CreateSeatGradeRequest request) {
        return CreateSeatGradeDto.builder()
                .grade(request.name())
                .build();
    }
}
