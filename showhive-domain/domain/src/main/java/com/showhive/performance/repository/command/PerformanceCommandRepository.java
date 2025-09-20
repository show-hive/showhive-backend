package com.showhive.performance.repository.command;

import com.showhive.performance.domain.Performance;

public interface PerformanceCommandRepository {
    Performance savePerformance(Performance performance);
}
