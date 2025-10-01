package com.showhive.admin.application.command.dto.category;

import com.showhive.category.domain.Category;
import java.util.List;

/**
 * 카테고리 부모와 자식의 트리 관계를 반환
 */
public record CategoryResult(
        Long id,
        String value,
        String description,
        Short sortOrder,
        List<CategoryResult> children
) {
    public static CategoryResult from(Category category) {
        return new CategoryResult(
                category.getId(),
                category.getValue(),
                category.getDescription(),
                category.getSortOrder(),
                category.getChildren().stream()
                        .map(CategoryResult::from)
                        .toList()
        );
    }
}
