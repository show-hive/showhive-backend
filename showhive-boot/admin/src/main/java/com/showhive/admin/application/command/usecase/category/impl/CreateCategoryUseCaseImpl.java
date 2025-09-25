package com.showhive.admin.application.command.usecase.category.impl;

import com.showhive.admin.application.command.dto.category.CreateCategoryDto;
import com.showhive.admin.application.command.usecase.category.CreateCategoryUseCase;
import com.showhive.category.domain.Category;
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
public class CreateCategoryUseCaseImpl implements CreateCategoryUseCase {
    private CategoryCommandRepository commandRepository;
    private CategoryQueryRepository queryRepository;

    @Override
    public void handle(CreateCategoryDto commandDto) {
        Category parentCategory = null;
        boolean existCategory = queryRepository.existsCategory(commandDto.value());
        if(commandDto.parentId() != null) {
            parentCategory = queryRepository.findById(commandDto.parentId())
                                            .orElseThrow(()-> new CategoryException(CategoryErrorCode.CATEGORY_NOT_FOUND));
        }

        Category category = Category.create(commandDto.groupCode(), parentCategory, commandDto.value(),
                commandDto.description(), commandDto.level(), commandDto.sortOrder(), commandDto.isActive());

        commandRepository.createCategory(category);
    }
}
