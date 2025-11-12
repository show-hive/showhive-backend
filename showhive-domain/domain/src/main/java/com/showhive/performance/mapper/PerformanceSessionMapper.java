package com.showhive.performance.mapper;

import com.showhive.performance.domain.PerformanceSession;
import com.showhive.performance.domain.PerformanceSessionId;
import com.showhive.performance.entity.PerformanceSessionEntity;
import org.springframework.stereotype.Component;

@Component
public class PerformanceSessionMapper {
    public PerformanceSessionEntity toEntity(PerformanceSession domain) {
        return PerformanceSessionEntity.builder()
                .id(domain.getId().getPerformanceSessionId())
                .startAt(domain.getStartAt())
                .endAt(domain.getEndAt())
                .sessionName(domain.getName())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }

    public PerformanceSession toDomain(PerformanceSessionEntity entity) {
        return PerformanceSession.builder()
                .id(PerformanceSessionId.builder()
                        .performanceSessionId(entity.getId())
                        .performanceId(entity.getPerformance().getId())
                        .build()
                )
                .startAt(entity.getStartAt())
                .endAt(entity.getEndAt())
                .name(entity.getSessionName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
