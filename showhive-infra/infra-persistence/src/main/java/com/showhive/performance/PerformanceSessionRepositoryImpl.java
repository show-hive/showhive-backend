package com.showhive.performance;

import com.showhive.performance.command.PerformanceSessionCommandJpaRepository;
import com.showhive.performance.domain.PerformanceSession;
import com.showhive.performance.exception.PerformanceErrorCode;
import com.showhive.performance.exception.PerformanceException;
import com.showhive.performance.repository.PerformanceSessionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PerformanceSessionRepositoryImpl implements PerformanceSessionRepository {
    private final PerformanceSessionCommandJpaRepository performanceSessionRepository;

    @Override
    public List<PerformanceSession> saveList(List<PerformanceSession> performanceSessions) {
        performanceSessions.forEach(performanceSession -> {
            boolean duplicate = performanceSessionRepository.existsPerformanceSession(
                    performanceSession.getPerformance().getId(), performanceSession.getStartAt(),
                    performanceSession.getEndAt());

            if (duplicate) {
                throw new PerformanceException(PerformanceErrorCode.PERFORMANCE_DUPLICATED);
            }
        });

        return performanceSessionRepository.saveAll(performanceSessions);
    }
}
