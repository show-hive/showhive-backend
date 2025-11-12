package com.showhive.performance;

import com.showhive.performance.command.PerformanceSessionCommandJpaRepository;
import com.showhive.performance.domain.PerformanceSession;
import com.showhive.performance.entity.PerformanceSessionEntity;
import com.showhive.performance.exception.PerformanceErrorCode;
import com.showhive.performance.exception.PerformanceException;
import com.showhive.performance.mapper.PerformanceSessionMapper;
import com.showhive.performance.repository.PerformanceSessionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PerformanceSessionRepositoryImpl implements PerformanceSessionRepository {
    private final PerformanceSessionCommandJpaRepository performanceSessionRepository;
    private final PerformanceSessionMapper performanceSessionMapper;

    @Override
    public List<PerformanceSession> saveList(List<PerformanceSession> performanceSessions) {
        performanceSessions.forEach(performanceSession -> {
            boolean duplicate = performanceSessionRepository.existsPerformanceSession(
                    performanceSession.getId().getPerformanceId(), performanceSession.getStartAt(),
                    performanceSession.getEndAt());

            if (duplicate) {
                throw new PerformanceException(PerformanceErrorCode.PERFORMANCE_SESSION_DUPLICATED);
            }
        });
        List<PerformanceSessionEntity> performanceSessionEntities =
                performanceSessions.stream()
                        .map(performanceSessionMapper::toEntity)
                        .toList();

        performanceSessionRepository.saveAll(performanceSessionEntities);

        return performanceSessionEntities.stream()
                .map(performanceSessionMapper::toDomain)
                .toList();
    }
}
