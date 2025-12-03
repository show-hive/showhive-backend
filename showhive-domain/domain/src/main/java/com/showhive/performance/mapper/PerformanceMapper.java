package com.showhive.performance.mapper;

import com.showhive.category.domain.CategoryId;
import com.showhive.category.entity.CategoryEntity;
import com.showhive.performance.domain.Performance;
import com.showhive.performance.domain.PerformanceId;
import com.showhive.performance.domain.PerformanceSessionId;
import com.showhive.performance.entity.PerformanceCategoryMapEntity;
import com.showhive.performance.entity.PerformanceEntity;
import com.showhive.performance.entity.PerformanceSessionEntity;
import com.showhive.venue.domain.VenueId;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class PerformanceMapper {
    public Performance toDomain(PerformanceEntity entity,
                                List<PerformanceCategoryMapEntity> performanceCategoryMapEntities) {
        if (entity == null) {
            return null;
        }

        List<CategoryId> categoryIds = Optional.ofNullable(performanceCategoryMapEntities)
                .orElse(Collections.emptyList())
                .stream()
                .map(mapEntity -> new CategoryId(mapEntity.getId().getCategoryId()))
                .toList();

        List<PerformanceSessionId> sessionIds = Optional.ofNullable(entity.getPerformanceSessions())
                .orElse(Collections.emptyList())
                .stream()
                .map(performanceSessionEntity ->
                        PerformanceSessionId.builder()
                                .performanceId(entity.getId())
                                .performanceSessionId(performanceSessionEntity.getId())
                                .build()
                )
                .toList();

        Duration runningTime = Optional.ofNullable(entity.getRunningTime())
                .map(Duration::ofSeconds)
                .orElse(Duration.ZERO);

        return Performance.builder()
                .id(PerformanceId.of(entity.getId()))
                .title(entity.getTitle())
                .venueId(VenueId.of(entity.getVenueId()))
                .runningTime(runningTime)
                .ageRating(entity.getAgeRating())
                .advantage(entity.getAdvantage())
                .performanceInfo(entity.getPerformanceInfo())
                .posterImageUrl(entity.getPosterImageUrl())
                .categoryIds(categoryIds)
                .bookStartedAt(entity.getBookStartedAt())
                .bookEndedAt(entity.getBookEndedAt())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .status(entity.getStatus())
                .sessions(sessionIds)
                .build();
    }

    public PerformanceEntity toEntity(Performance domain) {
        if (domain == null) {
            return null;
        }

        PerformanceEntity entity = PerformanceEntity.builder()
                .id(domain.getId() != null ? domain.getId().getPerformanceId() : null)
                .title(domain.getTitle())
                .venueId(domain.getVenueId() != null ? domain.getVenueId().getVenueId() : null)
                .runningTime(Optional.ofNullable(domain.getRunningTime())
                        .map(Duration::getSeconds)
                        .orElse(0L))
                .ageRating(domain.getAgeRating())
                .advantage(domain.getAdvantage())
                .performanceInfo(domain.getPerformanceInfo())
                .bookStartedAt(domain.getBookStartedAt())
                .bookEndedAt(domain.getBookEndedAt())
                .status(domain.getStatus())
                .posterImageUrl(domain.getPosterImageUrl())
                .build();

        List<PerformanceCategoryMapEntity> categories = Optional.ofNullable(domain.getCategoryIds())
                .orElse(Collections.emptyList())
                .stream()
                .map(categoryId -> PerformanceCategoryMapEntity.builder()
                        .performance(entity)
                        .category(CategoryEntity.builder()
                                .id(categoryId.id())
                                .build()
                        )
                        .build()
                )
                .toList();
        entity.addCategory(categories);

        List<PerformanceSessionEntity> performanceSessions = Optional.ofNullable(domain.getSessions())
                .orElse(Collections.emptyList())
                .stream()
                .map(sessionId -> PerformanceSessionEntity.builder()
                        .id(sessionId.getPerformanceSessionId())
                        .performance(entity)
                        .build()
                )
                .toList();

        entity.addPerformanceSession(performanceSessions);

        return entity;
    }
}
