package com.showhive.admin.application.command.usecase.category.impl;

import com.showhive.admin.application.command.usecase.category.DetailCategoryUseCase;
import com.showhive.admin.application.command.usecase.category.dto.CategoryResult;
import com.showhive.category.domain.Category;
import com.showhive.category.exception.CategoryErrorCode;
import com.showhive.category.exception.CategoryException;
import com.showhive.category.repository.command.CategoryCommandRepository;
import com.showhive.category.repository.query.CategoryQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class DetailCategoryUseCaseImpl implements DetailCategoryUseCase {
    private CategoryQueryRepository queryRepository;

    @Override
    public CategoryResult handle(Long id) {
        Category category = queryRepository.findByIdWithChildren(id)
                .orElseThrow(() -> new CategoryException(CategoryErrorCode.CATEGORY_NOT_FOUND));
        return CategoryResult.from(category);
    }
}
