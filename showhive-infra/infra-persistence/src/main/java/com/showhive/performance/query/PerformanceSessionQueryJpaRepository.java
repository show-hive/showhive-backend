package com.showhive.performance.query;

import com.showhive.performance.entity.PerformanceSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceSessionQueryJpaRepository extends JpaRepository<PerformanceSession, Long> {
}
