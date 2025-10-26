package com.showhive.performance.domain;

import com.showhive.category.domain.CategoryDomain;
import com.showhive.venue.domain.VenueDomain;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 공연 도메인
 */
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PerformanceDomain {

    private PerformanceId id;

    private String title;

    private VenueDomain venue;

    private Duration runningTime;

    private Short ageRating;

    private String advantage;

    private String performanceInfo;

    private String posterImageUrl;

    private CategoryDomain category;

    private LocalDateTime bookStartedAt;

    private LocalDateTime bookEndedAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<PerformanceSession> performanceSessions;
}
