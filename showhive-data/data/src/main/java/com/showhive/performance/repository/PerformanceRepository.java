package com.showhive.performance.repository;

import com.showhive.performance.domain.Performance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {
}
