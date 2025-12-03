package com.showhive.performance.domain;

import com.showhive.category.domain.CategoryId;
import com.showhive.category.exception.CategoryErrorCode;
import com.showhive.category.exception.CategoryException;
import com.showhive.performance.exception.PerformanceErrorCode;
import com.showhive.performance.exception.PerformanceException;
import com.showhive.venue.domain.VenueId;
import com.showhive.venue.exception.VenueErrorCode;
import com.showhive.venue.exception.VenueException;
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
public class Performance {

    private PerformanceId id;

    private String title;

    private VenueId venueId;

    private Duration runningTime;

    private Short ageRating;

    private String advantage;

    private String performanceInfo;

    private String posterImageUrl;

    private List<CategoryId> categoryIds;

    private LocalDateTime bookStartedAt;

    private LocalDateTime bookEndedAt;

    private List<PerformanceSessionId> sessions;

    private PerformanceStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static Performance create(
            String title,
            VenueId venueId,
            Duration runningTime,
            Short ageRating,
            String advantage,
            String performanceInfo,
            String posterImageUrl,
            List<PerformanceCategoryMap> performanceCategoryMaps,
            LocalDateTime bookStartedAt,
            LocalDateTime bookEndedAt) {

        if (venueId == null) {
            throw new VenueException(VenueErrorCode.VENUE_NOT_FOUND);
        }

        if (title == null || title.isBlank()) {
            throw new PerformanceException(PerformanceErrorCode.CASTING_INVALID_INPUT);
        }

        if (runningTime == null || runningTime.isNegative() || runningTime.isZero()) {
            throw new PerformanceException(PerformanceErrorCode.RUNNING_TIME_NOT_VALID);
        }

        if (bookStartedAt == null || bookEndedAt == null) {
            throw new PerformanceException(PerformanceErrorCode.BOOKED_TIME_NOT_VALID);
        }

        if (bookEndedAt.isBefore(bookStartedAt)) {
            throw new PerformanceException(PerformanceErrorCode.BOOKED_TIME_NOT_VALID);
        }

        if (ageRating != null && ageRating < 0) {
            throw new PerformanceException(PerformanceErrorCode.INPUT_NOT_VALID);
        }

        List<CategoryId> categoryIds = new java.util.ArrayList<>();

        if (performanceCategoryMaps != null && !performanceCategoryMaps.isEmpty()) {
            performanceCategoryMaps.forEach(performanceCategoryMap -> {
                if (performanceCategoryMap == null || performanceCategoryMap.getId() == null
                        || performanceCategoryMap.getId().getPerformanceId() == null) {
                    throw new CategoryException(CategoryErrorCode.CATEGORY_VALUE_NOT_VALID);
                } else {
                    categoryIds.add(new CategoryId(performanceCategoryMap.getId().getCategoryId()));
                }
            });
        }

        return Performance.builder()
                .title(title)
                .venueId(venueId)
                .runningTime(runningTime)
                .ageRating(ageRating)
                .advantage(advantage)
                .performanceInfo(performanceInfo)
                .posterImageUrl(posterImageUrl)
                .categoryIds(categoryIds)
                .build();
    }
}
