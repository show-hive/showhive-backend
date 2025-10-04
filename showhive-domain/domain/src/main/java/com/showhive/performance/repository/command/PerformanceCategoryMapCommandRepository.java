package com.showhive.performance.repository.command;

import com.showhive.performance.domain.PerformanceCategoryMap;
import java.util.List;

public interface PerformanceCategoryMapCommandRepository {
    void savePerformanceCategoryList(List<PerformanceCategoryMap> performanceCategoryMaps);

    void savePerformanceCategory(PerformanceCategoryMap performanceCategoryMap);
}
