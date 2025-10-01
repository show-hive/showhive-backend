package com.showhive.admin.application.command.usecase.category;

import com.showhive.admin.application.command.dto.category.CategoryResult;

public interface DetailCategoryUseCase {
    CategoryResult handle(Long id);
}
