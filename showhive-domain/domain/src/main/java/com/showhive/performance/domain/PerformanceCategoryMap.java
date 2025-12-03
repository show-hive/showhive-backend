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
public class PerformanceCategoryMap {
    private PerformanceCategoryId id;
    private Integer priority;

    public static PerformanceCategoryMap create(PerformanceCategoryId id, Integer priority) {
        return PerformanceCategoryMap.builder()
                .id(id)
                .priority(priority)
                .build();
    }
}
