package com.showhive.category.domain;

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
public class CategoryDomain {

    private CategoryId id;

    private String groupCode;

    private CategoryDomain parent;

    private String value;

    private String description;

    private Short level;

    private Short sortOrder;

    private Boolean isActive;

    private List<CategoryDomain> children;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public void deactivate() {
        this.isActive = false;
    }
}
