package com.showhive.performance.repository.command;

import com.showhive.performance.domain.PerformanceSession;

public interface SessionCommandRepository {
    PerformanceSession saveSession(PerformanceSession performanceSession);
}
