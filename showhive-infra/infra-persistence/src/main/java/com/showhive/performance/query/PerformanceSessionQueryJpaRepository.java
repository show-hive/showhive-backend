package com.showhive.performance.query;

import com.showhive.performance.entity.PerformanceSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceSessionQueryJpaRepository extends JpaRepository<PerformanceSessionEntity, Long> {
}
