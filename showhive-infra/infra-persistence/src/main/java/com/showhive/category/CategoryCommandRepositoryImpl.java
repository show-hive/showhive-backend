package com.showhive.category;

import com.showhive.category.command.CategoryCommandJpaRepository;
import com.showhive.category.entity.Category;
import com.showhive.category.query.CategoryQueryJpaRepository;
import com.showhive.category.repository.command.CategoryCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryCommandRepositoryImpl implements CategoryCommandRepository {
    private final CategoryCommandJpaRepository commandRepository;
    private final CategoryQueryJpaRepository queryRepository;

    @Override
    public Category saveCategory(Category category) {
        return commandRepository.save(category);
    }
}
