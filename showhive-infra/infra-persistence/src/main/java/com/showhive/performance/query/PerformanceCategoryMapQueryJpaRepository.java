package com.showhive.performance.query;

import java.util.Collection;
import java.util.List;
import com.showhive.performance.entity.PerformanceCategoryMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceCategoryMapQueryJpaRepository extends JpaRepository<PerformanceCategoryMap, Long> {
    List<PerformanceCategoryMap> findByPerformance_Id(Long performanceId);

    PerformanceCategoryMap findByCategory_Id(Long categoryCategoryId);

    List<PerformanceCategoryMap> findAllByCategory_IdIn(Collection<Long> categoryIds);
}
