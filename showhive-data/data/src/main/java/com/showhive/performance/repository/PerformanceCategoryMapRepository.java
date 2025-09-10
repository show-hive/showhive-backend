package com.showhive.performance.repository;

import com.showhive.performance.domain.PerformanceCategoryId;
import com.showhive.performance.domain.PerformanceCategoryMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceCategoryMapRepository extends JpaRepository<PerformanceCategoryMap, PerformanceCategoryId> {
}
