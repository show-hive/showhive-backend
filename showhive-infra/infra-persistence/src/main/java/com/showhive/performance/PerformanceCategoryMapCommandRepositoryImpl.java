package com.showhive.performance;

import com.showhive.performance.domain.PerformanceCategoryMap;
import com.showhive.performance.query.PerformanceCategoryMapQueryJpaRepository;
import com.showhive.performance.repository.command.PerformanceCategoryMapCommandRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PerformanceCategoryMapCommandRepositoryImpl implements PerformanceCategoryMapCommandRepository {
    private final PerformanceCategoryMapQueryJpaRepository jpaRepository;

    @Override
    public void savePerformanceCategoryList(List<PerformanceCategoryMap> performanceCategoryMaps) {
        jpaRepository.saveAll(performanceCategoryMaps);
    }

    @Override
    public void savePerformanceCategory(PerformanceCategoryMap performanceCategoryMap) {
        jpaRepository.save(performanceCategoryMap);
    }
}
