package com.showhive.admin.application.command.usecase.category.impl;

import com.showhive.admin.application.command.usecase.category.DeleteCategoryUseCase;
import com.showhive.category.entity.Category;
import com.showhive.category.exception.CategoryErrorCode;
import com.showhive.category.exception.CategoryException;
import com.showhive.category.repository.command.CategoryCommandRepository;
import com.showhive.category.repository.query.CategoryQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class DeleteCategoryUseCaseImpl implements DeleteCategoryUseCase {
    private final CategoryCommandRepository commandRepository;
    private final CategoryQueryRepository categoryQueryRepository;

    @Override
    public void handle(Long id) {
        Category category = categoryQueryRepository.findById(id)
                .orElseThrow(() -> new CategoryException(CategoryErrorCode.CATEGORY_NOT_FOUND));
        // 이미 삭제된 항목이면 에러 발생
        category.deactivate();

        commandRepository.saveCategory(category);
    }
}
