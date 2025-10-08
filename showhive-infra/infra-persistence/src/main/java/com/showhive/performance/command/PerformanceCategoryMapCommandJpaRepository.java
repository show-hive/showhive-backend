package com.showhive.performance.command;

import com.showhive.performance.entity.PerformanceCategoryMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceCategoryMapCommandJpaRepository extends JpaRepository<PerformanceCategoryMap, Long> {
}
