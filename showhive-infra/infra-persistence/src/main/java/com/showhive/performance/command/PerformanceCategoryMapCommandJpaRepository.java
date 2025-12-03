package com.showhive.performance.command;

import com.showhive.performance.entity.PerformanceCategoryMapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceCategoryMapCommandJpaRepository extends JpaRepository<PerformanceCategoryMapEntity, Long> {
}
