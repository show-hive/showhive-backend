package com.showhive.performance.repository.command;

import com.showhive.performance.entity.Performance;

public interface PerformanceCommandRepository {
    Performance savePerformance(Performance performance);
}
