package com.showhive.performance.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PerformanceCategoryId {

    private Long performanceId;
    private Long categoryId;

    public static PerformanceCategoryId of(Long performanceId, Long categoryId) {
        return PerformanceCategoryId.builder()
                .performanceId(performanceId)
                .categoryId(categoryId)
                .build();
    }
}
