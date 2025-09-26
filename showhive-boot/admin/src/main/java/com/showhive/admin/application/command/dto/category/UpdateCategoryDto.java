package com.showhive.admin.application.command.dto.category;

public record UpdateCategoryDto(
        String groupCode,
        Long parentId,
        String value,
        String description,
        Short level,
        Short sortOrder,
        Boolean isActive
) {
}
