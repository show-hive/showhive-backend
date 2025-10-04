package com.showhive.category;

import com.showhive.category.domain.Category;
import com.showhive.category.query.CategoryQueryJpaRepository;
import com.showhive.category.repository.query.CategoryQueryRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryQueryRepositoryImpl implements CategoryQueryRepository {
    private final CategoryQueryJpaRepository queryRepository;

    @Override
    public Optional<Category> findById(Long id) {
        return queryRepository.findById(id);
    }

    @Override
    public List<Category> findByIds(List<Long> categoryIds) {
        return queryRepository.findAllByIdIn(categoryIds);
    }

    @Override
    public boolean existsCategory(String groupCode, String value) {
        return queryRepository.existsByGroupCodeAndValue(groupCode, value);
    }

    @Override
    public Optional<Category> findByIdWithChildren(Long id) {
        return queryRepository.findWithChildrenById(id);
    }

    @Override
    public Optional<Category> findByValueWithChildren(String value) {
        return queryRepository.findWithChildrenByValue(value);
    }
}
