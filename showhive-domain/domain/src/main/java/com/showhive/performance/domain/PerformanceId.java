package com.showhive.performance.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PerformanceId {
    private Long performanceId;

    public static PerformanceId of(Long performanceId) {
        return PerformanceId.builder()
                .performanceId(performanceId)
                .build();
    }
}
