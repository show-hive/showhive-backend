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
    private final CategoryCommandRepository commandRepository;
    private final CategoryQueryRepository queryRepository;

    @Override
    public void handle(CreateCategoryDto commandDto) {
        Category category = null;

        // 카테고리 그룹코드, 값으로 중복 검사
        boolean existCategory = queryRepository.existsCategory(commandDto.groupCode(), commandDto.value());

        if (existCategory) {
            throw new CategoryException(CategoryErrorCode.CATEGORY_NOT_FOUND);
        }

        // 부모 카테고리 존재시 Root가 아닌 Node로 추가 됨
        if (commandDto.parentId() != null) {
            Category parentCategory = queryRepository.findById(commandDto.parentId())
                                                     .orElseThrow(() -> new CategoryException(CategoryErrorCode.CATEGORY_NOT_FOUND));

            category = Category.createNodeCategory(commandDto.groupCode(), parentCategory, commandDto.value(),
                    commandDto.description(), commandDto.sortOrder(), commandDto.isActive());
        } else {
            category = Category.createRoot(commandDto.groupCode(), commandDto.value(),
                    commandDto.description(), commandDto.sortOrder(), commandDto.isActive());
        }

        commandRepository.createCategory(category);
    }
}
