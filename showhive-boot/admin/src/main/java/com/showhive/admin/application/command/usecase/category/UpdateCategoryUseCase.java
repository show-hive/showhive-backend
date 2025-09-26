package com.showhive.admin.application.command.usecase.category;

import com.showhive.admin.application.command.dto.category.UpdateCategoryDto;

public interface UpdateCategoryUseCase {
    void handle(Long id, UpdateCategoryDto commandDto);
}
