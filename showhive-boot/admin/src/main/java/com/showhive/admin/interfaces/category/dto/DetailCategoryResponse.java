package com.showhive.admin.interfaces.category.dto;

import com.showhive.admin.application.command.dto.category.CategoryResult;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "상세 카테고리 반환")
public record DetailCategoryResponse(
        @Schema(description = "카테고리 ID")
        Long id,
        @Schema(description = "카테고리 코드값")
        String value,
        @Schema(description = "설명")
        String description,
        @Schema(description = "정렬 순서")
        Short sortOrder,
        @Schema(description = "하위 카테고리")
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
