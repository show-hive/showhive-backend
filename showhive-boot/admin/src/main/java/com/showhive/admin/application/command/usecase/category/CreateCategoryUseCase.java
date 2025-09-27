package com.showhive.admin.application.command.usecase.category;

import com.showhive.admin.application.command.dto.category.CreateCategoryDto;

public interface CreateCategoryUseCase {
    void handle(CreateCategoryDto commandDto);
}
