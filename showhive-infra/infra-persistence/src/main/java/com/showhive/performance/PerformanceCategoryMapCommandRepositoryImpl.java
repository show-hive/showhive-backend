package com.showhive.performance;

import com.showhive.performance.domain.PerformanceCategoryMap;
import com.showhive.performance.entity.PerformanceCategoryMapEntity;
import com.showhive.performance.mapper.PerformanceCategoryMapper;
import com.showhive.performance.query.PerformanceCategoryMapQueryJpaRepository;
import com.showhive.performance.repository.command.PerformanceCategoryMapCommandRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PerformanceCategoryMapCommandRepositoryImpl implements PerformanceCategoryMapCommandRepository {
    private final PerformanceCategoryMapQueryJpaRepository jpaRepository;
    private final PerformanceCategoryMapper performanceCategoryMapper;

    @Override
    public void savePerformanceCategoryList(List<PerformanceCategoryMap> performanceCategoryMaps) {
        List<PerformanceCategoryMapEntity> performanceCategoryMapEntities =
                performanceCategoryMaps.stream()
                        .map(performanceCategoryMapper::toEntity)
                        .toList();

        jpaRepository.saveAll(performanceCategoryMapEntities);
    }

    @Override
    public void savePerformanceCategory(PerformanceCategoryMap performanceCategoryMap) {
        jpaRepository.save(performanceCategoryMapper.toEntity(performanceCategoryMap));
    }
}
