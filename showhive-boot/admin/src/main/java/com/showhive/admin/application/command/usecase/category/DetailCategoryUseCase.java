package com.showhive.admin.application.command.usecase.category;

import com.showhive.admin.application.command.dto.category.DetailCategoryResult;

public interface DetailCategoryUseCase {
    DetailCategoryResult handle(Long id);
}
