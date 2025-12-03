package com.showhive.performance.command;

import com.showhive.performance.entity.PerformanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceCommandJpaRepository extends JpaRepository<PerformanceEntity, Long> {
}
