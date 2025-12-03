package com.showhive.admin.application.command.dto.category;

import com.showhive.category.domain.Category;
import java.util.List;

/**
 * 카테고리 부모와 자식의 트리 관계를 반환
 */
public record DetailCategoryResult(
        Long id,
        String value,
        String description,
        Short sortOrder,
        List<DetailCategoryResult> children
) {
    public static DetailCategoryResult from(Category category) {
        return new DetailCategoryResult(
                category.getId().id(),
                category.getValue(),
                category.getDescription(),
                category.getSortOrder(),
                category.getChildren().stream()
                        .map(DetailCategoryResult::from)
                        .toList()
        );
    }
}
