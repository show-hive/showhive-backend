package com.showhive.category.domain;

import com.showhive.category.exception.CategoryErrorCode;
import com.showhive.category.exception.CategoryException;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    private CategoryId id;

    private String groupCode;

    private CategoryId parentId;

    private String value;

    private String description;

    private Short level;

    private Short sortOrder;

    private Boolean isActive;

    private List<Category> children;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static Category createNodeCategory(String groupCode, Category parent, String value,
                                              String description,
                                              Short sortOrder, Boolean isActive) {
        if (parent == null) {
            throw new CategoryException(CategoryErrorCode.CATEGORY_PARENT_NOT_FOUND);
        }
        short childLevel = parent.getLevel() != null ? (short) (parent.getLevel() + 1) : 0;

        return Category.builder()
                .groupCode(groupCode)
                .parentId(parent.getId())
                .value(value)
                .description(description)
                .level(childLevel)
                .sortOrder(sortOrder)
                .isActive(isActive)
                .build();
    }

    public static Category createRoot(String groupCode, String value,
                                      String description,
                                      Short sortOrder, Boolean isActive) {
        return Category.builder()
                .groupCode(groupCode)
                .value(value)
                .description(description)
                .level((short) 0)
                .sortOrder(sortOrder)
                .isActive(isActive)
                .build();
    }

    public void updateNode(String groupCode, CategoryId parentId, String value, String description, Short level,
                           Short sortOrder, Boolean isActive) {
        this.groupCode = groupCode;
        this.parentId = parentId;
        this.value = value;
        this.description = description;
        this.level = level;
        this.sortOrder = sortOrder;
        this.isActive = isActive;
    }

    public void updateRoot(String groupCode, String value, String description, Short level, Short sortOrder,
                           Boolean isActive) {
        this.groupCode = groupCode;
        this.value = value;
        this.description = description;
        this.level = level;
        this.sortOrder = sortOrder;
        this.isActive = isActive;
    }

    public void deactivate() {
        this.isActive = false;
    }
}
