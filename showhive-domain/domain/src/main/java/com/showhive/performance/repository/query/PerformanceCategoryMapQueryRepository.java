package com.showhive.performance.repository.query;

import com.showhive.performance.domain.PerformanceCategoryMap;
import java.util.List;

public interface PerformanceCategoryMapQueryRepository {
    PerformanceCategoryMap findByCategoryId(Long categoryId);

    List<PerformanceCategoryMap> findByCategoryIds(List<Long> categoryIds);

    List<PerformanceCategoryMap> findByPerformanceId(Long performanceId);
}
