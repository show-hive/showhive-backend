package com.showhive.performance.mapper;

import com.showhive.performance.domain.PerformanceCategoryId;
import com.showhive.performance.domain.PerformanceCategoryMap;
import com.showhive.performance.entity.PerformanceCategoryEntityId;
import com.showhive.performance.entity.PerformanceCategoryMapEntity;
import org.springframework.stereotype.Component;

@Component
public class PerformanceCategoryMapper {
    public PerformanceCategoryMap toDomain(PerformanceCategoryMapEntity entity) {
        PerformanceCategoryEntityId entityId = entity.getId();
        return PerformanceCategoryMap.builder()
                .id(PerformanceCategoryId
                        .builder()
                        .performanceId(entityId.getPerformanceId())
                        .categoryId(entityId.getCategoryId())
                        .build()
                )
                .priority(entity.getPriority())
                .build();
    }

    public PerformanceCategoryMapEntity toEntity(PerformanceCategoryMap domain) {
        PerformanceCategoryId performanceCategoryId = domain.getId();
        return PerformanceCategoryMapEntity.builder()
                .id(new PerformanceCategoryEntityId(
                                performanceCategoryId.getPerformanceId(),
                                performanceCategoryId.getCategoryId()
                        )
                )
                .priority(domain.getPriority())
                .build();
    }
}
