package com.showhive.admin.interfaces.performance.dto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public record CreatePerformanceRequest(
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
}
