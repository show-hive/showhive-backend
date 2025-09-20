package com.showhive.performance.command;

import com.showhive.performance.domain.Performance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceCommandJpaRepository extends JpaRepository<Performance,Long> {
}
