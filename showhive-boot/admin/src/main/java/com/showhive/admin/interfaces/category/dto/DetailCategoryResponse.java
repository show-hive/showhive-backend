package com.showhive.admin.interfaces.category.dto;

import com.showhive.admin.application.command.usecase.category.dto.CategoryResult;
import java.util.List;

public record DetailCategoryResponse (
        Long id,
        String value,
        String description,
        Short sortOrder,
        List<DetailCategoryResponse> children
) {
    public static DetailCategoryResponse from(CategoryResult category) {
        return new DetailCategoryResponse(
                category.id(),
                category.value(),
                category.description(),
                category.sortOrder(),
                category.children().stream()
                        .map(DetailCategoryResponse::from)
                        .toList()
        );
    }
}
