package com.showhive.performance;

import com.showhive.performance.domain.PerformanceCategoryMap;
import com.showhive.performance.entity.PerformanceCategoryMapEntity;
import com.showhive.performance.mapper.PerformanceCategoryMapper;
import com.showhive.performance.query.PerformanceCategoryMapQueryJpaRepository;
import com.showhive.performance.repository.command.PerformanceCategoryMapCommandRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional
public class PerformanceCategoryMapCommandRepositoryImpl implements PerformanceCategoryMapCommandRepository {
    private final PerformanceCategoryMapQueryJpaRepository jpaRepository;
    private final PerformanceCategoryMapper performanceCategoryMapper;

    @Override
    public List<PerformanceCategoryMap> savePerformanceCategoryList(
            List<PerformanceCategoryMap> performanceCategoryMaps) {
        List<PerformanceCategoryMapEntity> performanceCategoryMapEntities =
                performanceCategoryMaps.stream()
                        .map(performanceCategoryMapper::toEntity)
                        .toList();

        jpaRepository.saveAll(performanceCategoryMapEntities);

        return performanceCategoryMapEntities.stream()
                .map(performanceCategoryMapper::toDomain)
                .toList();
    }

    @Override
    public PerformanceCategoryMap savePerformanceCategory(PerformanceCategoryMap performanceCategoryMap) {
        PerformanceCategoryMapEntity performanceCategoryMapEntity =
                jpaRepository.save(performanceCategoryMapper.toEntity(performanceCategoryMap));

        return performanceCategoryMapper.toDomain(performanceCategoryMapEntity);
    }
}
