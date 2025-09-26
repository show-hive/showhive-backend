package com.showhive.admin.interfaces.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateCategoryRequest(
        @NotNull @NotBlank
        String groupCode,
        Long parentId,
        @NotNull @NotBlank
        String value,
        @NotBlank
        String description,
        Short level,
        Short sortOrder,
        Boolean isActive
) {
}
