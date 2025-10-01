package com.showhive.admin.application.command.usecase.category;

import com.showhive.admin.application.command.usecase.category.dto.CategoryResult;

public interface DetailCategoryUseCase {
    CategoryResult handle(Long id);
}
