package com.showhive.performance.query;

import com.showhive.performance.domain.PerformanceSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceSessionQueryJpaRepository extends JpaRepository<PerformanceSession, Long> {
}
