package com.showhive.admin.application.command.usecase.category.impl;

import com.showhive.admin.application.command.dto.category.UpdateCategoryDto;
import com.showhive.admin.application.command.usecase.category.UpdateCategoryUseCase;
import com.showhive.category.entity.Category;
import com.showhive.category.exception.CategoryErrorCode;
import com.showhive.category.exception.CategoryException;
import com.showhive.category.repository.command.CategoryCommandRepository;
import com.showhive.category.repository.query.CategoryQueryRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class UpdateCategoryUseCaseImpl implements UpdateCategoryUseCase {
    private final CategoryCommandRepository commandRepository;
    private final CategoryQueryRepository queryRepository;

    @Override
    public void handle(Long id, UpdateCategoryDto commandDto) {
        Category category = queryRepository.findById(id)
                .orElseThrow(() -> new CategoryException(CategoryErrorCode.CATEGORY_NOT_FOUND));

        // 카테고리 그룹코드, 값으로 중복 검사
        Optional<Category> existCategoryOptional = queryRepository.findCategoryByGroupCodeAndValue(
                commandDto.groupCode(), commandDto.value());

        if (existCategoryOptional.isPresent()) {
            // 변경 값이 본인과 다르지 않다면 오류 발생시킴
            boolean isSelf = existCategoryOptional.get().getId().equals(id);

            if (!isSelf) {
                throw new CategoryException(CategoryErrorCode.CATEGORY_NOT_FOUND);
            }
        }

        // 부모 카테고리 존재시 Root가 아닌 Node로 추가 됨
        if (commandDto.parentId() != null) {
            Category parentCategory = queryRepository.findById(commandDto.parentId())
                    .orElseThrow(() -> new CategoryException(CategoryErrorCode.CATEGORY_NOT_FOUND));

            category.updateNode(commandDto.groupCode(), parentCategory, commandDto.value(),
                    commandDto.description(), commandDto.level(), commandDto.sortOrder(), commandDto.isActive());
        } else {
            category.updateRoot(commandDto.groupCode(), commandDto.value(),
                    commandDto.description(), commandDto.level(), commandDto.sortOrder(), commandDto.isActive());
        }

        commandRepository.saveCategory(category);
    }
}
