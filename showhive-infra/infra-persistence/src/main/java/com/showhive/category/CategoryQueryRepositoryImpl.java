package com.showhive.category;

import com.showhive.category.domain.Category;
import com.showhive.category.query.CategoryQueryJpaRepository;
import com.showhive.category.repository.query.CategoryQueryRepository;
import com.showhive.performance.mapper.CategoryMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryQueryRepositoryImpl implements CategoryQueryRepository {
    private final CategoryQueryJpaRepository queryRepository;
    private final CategoryMapper categoryDomainMapper;

    @Override
    public Optional<Category> findById(Long id) {

        return queryRepository.findById(id)
                .map(categoryDomainMapper::toDomain);
    }

    @Override
    public List<Category> findByIds(List<Long> categoryIds) {
        return queryRepository.findAllByIdIn(categoryIds)
                .stream()
                .map(categoryDomainMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsCategory(String groupCode, String value) {
        return queryRepository.existsByGroupCodeAndValue(groupCode, value);
    }

    @Override
    public Optional<Category> findCategoryByGroupCodeAndValue(String groupCode, String value) {
        return queryRepository.findOneByGroupCodeAndValue(groupCode, value)
                .map(categoryDomainMapper::toDomain);
    }

    @Override
    public Optional<Category> findByIdWithChildren(Long id) {
        return queryRepository.findWithChildrenById(id)
                .map(categoryDomainMapper::toDomain);
    }

    @Override
    public Optional<Category> findByValueWithChildren(String value) {
        return queryRepository.findWithChildrenByValue(value)
                .map(categoryDomainMapper::toDomain);
    }
}
