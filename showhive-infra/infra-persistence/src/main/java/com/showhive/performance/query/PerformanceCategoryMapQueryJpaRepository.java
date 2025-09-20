package com.showhive.performance.query;

import com.showhive.performance.domain.PerformanceCategoryMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceCategoryMapQueryJpaRepository extends JpaRepository<PerformanceCategoryMap, Long> {
}
