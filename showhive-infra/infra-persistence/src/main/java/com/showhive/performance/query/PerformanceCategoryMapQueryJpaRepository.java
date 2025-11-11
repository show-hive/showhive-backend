package com.showhive.performance.query;

import com.showhive.performance.entity.PerformanceCategoryMapEntity;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceCategoryMapQueryJpaRepository extends JpaRepository<PerformanceCategoryMapEntity, Long> {
    List<PerformanceCategoryMapEntity> findAllByPerformanceId(Long performanceId);

    PerformanceCategoryMapEntity findByCategory_Id(Long categoryCategoryId);

    List<PerformanceCategoryMapEntity> findAllByCategory_IdIn(Collection<Long> categoryIds);


}
