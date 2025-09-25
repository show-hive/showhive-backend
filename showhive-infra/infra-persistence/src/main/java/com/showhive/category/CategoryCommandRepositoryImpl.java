package com.showhive.category;

import com.showhive.category.command.CategoryCommandJpaRepository;
import com.showhive.category.domain.Category;
import com.showhive.category.repository.command.CategoryCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryCommandRepositoryImpl implements CategoryCommandRepository {
    private final CategoryCommandJpaRepository commandRepository;

    @Override
    public Category createCategory(Category category) {
        return commandRepository.save(category);
    }
}
