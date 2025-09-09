package com.showhive.performance.repository;

import com.showhive.performance.domain.PerformanceSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceSessionRepository extends JpaRepository<PerformanceSession, Long> {
}
