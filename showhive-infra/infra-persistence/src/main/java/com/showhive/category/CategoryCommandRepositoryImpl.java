package com.showhive.category;

import com.showhive.category.command.CategoryCommandJpaRepository;
import com.showhive.category.domain.Category;
import com.showhive.category.exception.CategoryErrorCode;
import com.showhive.category.exception.CategoryException;
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
    public Category createCategory(Category category) {
        return commandRepository.save(category);
    }

    @Override
    public void deActiveCategory(Long id) {
        Category category = queryRepository.findByIdAndIsActiveIsTrue(id)
                .orElseThrow(() -> new CategoryException(CategoryErrorCode.CATEGORY_NOT_FOUND));

        category.deactivate();

        commandRepository.save(category);
    }
}
