package com.showhive.performance.repository.command;

import com.showhive.performance.domain.PerformanceCategoryMap;
import java.util.List;

public interface PerformanceCategoryMapCommandRepository {
    List<PerformanceCategoryMap> savePerformanceCategoryList(List<PerformanceCategoryMap> performanceCategoryMaps);

    PerformanceCategoryMap savePerformanceCategory(PerformanceCategoryMap performanceCategoryMap);
}
