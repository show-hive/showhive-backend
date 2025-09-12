package com.showhive.admin.interfaces.performance.dto;

import java.time.Duration;
import java.time.LocalDateTime;

public record CreatePerformanceRequest(
        String title,
        Long venueId,
        Duration runningTime,
        Short ageRating,
        String advantage,
        String performanceInfo,
        LocalDateTime bookStartedAt,
        LocalDateTime bookEndedAt
) {
}
