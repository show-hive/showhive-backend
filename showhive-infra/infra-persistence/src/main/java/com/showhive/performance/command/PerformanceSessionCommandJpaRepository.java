package com.showhive.performance.command;

import com.showhive.performance.domain.PerformanceSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceSessionCommandJpaRepository extends JpaRepository<PerformanceSession, Long> {
}
