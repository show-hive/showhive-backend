package com.showhive.admin.application.command.dto;

import com.showhive.admin.interfaces.performance.dto.CreatePerformanceRequest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record CreatePerformanceDto(
        String title,
        Long venueId,
        Duration runningTime,
        Short ageRating,
        String advantage,
        String performanceInfo,
        List<Long> categoryIds,
        LocalDateTime bookStartedAt,
        LocalDateTime bookEndedAt
) {
    public static CreatePerformanceDto of(CreatePerformanceRequest request) {
        return CreatePerformanceDto.builder()
                .venueId(request.venueId())
                .runningTime(request.runningTime())
                .ageRating(request.ageRating())
                .advantage(request.advantage())
                .performanceInfo(request.performanceInfo())
                .categoryIds(request.categoryIds())
                .bookStartedAt(request.bookStartedAt())
                .bookEndedAt(request.bookEndedAt())
                .build();
    }
}
