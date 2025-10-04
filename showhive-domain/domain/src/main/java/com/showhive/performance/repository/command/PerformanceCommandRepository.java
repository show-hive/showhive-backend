package com.showhive.performance.repository.command;

import com.showhive.performance.domain.Performance;
import java.util.Optional;

public interface PerformanceCommandRepository {
    Performance savePerformance(Performance performance);

    Optional<Performance> findPerformanceById(Long id);

    boolean existsPerformanceById(Long id);
}
