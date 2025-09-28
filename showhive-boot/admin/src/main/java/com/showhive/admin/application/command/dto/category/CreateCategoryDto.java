package com.showhive.admin.application.command.dto.category;

import com.showhive.admin.interfaces.category.dto.CreateCategoryRequest;

public record CreateCategoryDto(
        String groupCode,
        Long parentId,
        String value,
        String description,
        Short level,
        Short sortOrder,
        Boolean isActive
) {
    
    public static CreateCategoryDto of(CreateCategoryRequest request) {

        return new CreateCategoryDto(request.groupCode(),
                request.parentId(), request.value(), request.description(),
                request.level(), request.level(), request.isActive());
    }
}
