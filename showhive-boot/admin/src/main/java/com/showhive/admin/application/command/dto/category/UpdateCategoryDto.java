package com.showhive.admin.application.command.dto.category;

import com.showhive.admin.interfaces.category.dto.UpdateCategoryRequest;

public record UpdateCategoryDto(
        String groupCode,
        Long parentId,
        String value,
        String description,
        Short level,
        Short sortOrder,
        Boolean isActive
) {
    public static UpdateCategoryDto of(UpdateCategoryRequest request) {

        return new UpdateCategoryDto(request.groupCode(),
                request.parentId(), request.value(), request.description(),
                request.level(), request.level(), request.isActive());
    }
}
