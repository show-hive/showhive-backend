package com.showhive.category;

import com.showhive.category.command.CategoryCommandJpaRepository;
import com.showhive.category.domain.Category;
import com.showhive.category.entity.CategoryEntity;
import com.showhive.category.query.CategoryQueryJpaRepository;
import com.showhive.category.repository.command.CategoryCommandRepository;
import com.showhive.performance.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryCommandRepositoryImpl implements CategoryCommandRepository {
    private final CategoryCommandJpaRepository commandRepository;
    private final CategoryQueryJpaRepository queryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Category saveCategory(Category category) {
        CategoryEntity entity = categoryMapper.toEntity(category);
        commandRepository.save(entity);

        return categoryMapper.toDomain(entity);
    }
}
