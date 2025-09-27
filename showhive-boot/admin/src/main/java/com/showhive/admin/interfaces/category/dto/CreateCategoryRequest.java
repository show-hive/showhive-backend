package com.showhive.admin.interfaces.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(name = "카테고리 생성 객체")
public record CreateCategoryRequest(
        @Schema(name = "그룹 코드", example = "MENU", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        String groupCode,

        @Schema(name = "부모 카테고리 ID", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Long parentId,

        @Schema(name = "값", example = "홈", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull @NotBlank
        String value,

        @Schema(name = "카테고리에 대한 설명", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @NotBlank
        String description,

        @Schema(name = "깊이", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Short level,

        @Schema(name = "깊이", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Short sortOrder,

        @Schema(name = "사용 여부", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Boolean isActive
) {
}
