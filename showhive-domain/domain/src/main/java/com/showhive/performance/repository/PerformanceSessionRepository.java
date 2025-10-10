package com.showhive.performance.repository;

import com.showhive.performance.domain.PerformanceSession;
import java.util.List;

public interface PerformanceSessionRepository {
    List<PerformanceSession> saveList(List<PerformanceSession> performanceSessions);
}
