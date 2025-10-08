package com.showhive.performance.command;

import com.showhive.performance.entity.PerformanceSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceSessionCommandJpaRepository extends JpaRepository<PerformanceSession, Long> {
}
