package com.showhive.performance.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PerformanceSessionId {
    private Long performanceSessionId;
    private Long performanceId;

    public static PerformanceSessionId of(Long performanceSessionId, Long performanceId) {
        return PerformanceSessionId.builder()
                .performanceSessionId(performanceSessionId)
                .performanceId(performanceId)
                .build();
    }
}
