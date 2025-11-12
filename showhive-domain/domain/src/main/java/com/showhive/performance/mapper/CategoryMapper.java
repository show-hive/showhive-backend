package com.showhive.performance.mapper;

import com.showhive.category.domain.Category;
import com.showhive.category.domain.CategoryId;
import com.showhive.category.entity.CategoryEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toDomain(CategoryEntity entity) {
        if (entity == null) {
            return null;
        }

        return Category.builder()
                .id(new CategoryId(entity.getId()))
                .groupCode(entity.getGroupCode())
                .parentId(entity.getParent() != null ? new CategoryId(entity.getParent().getId()) : null)
                .value(entity.getValue())
                .description(entity.getDescription())
                .level(entity.getLevel())
                .sortOrder(entity.getSortOrder())
                .isActive(entity.getIsActive())
                .children(toDomainList(entity.getChildren()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public CategoryEntity toEntity(Category domain) {
        if (domain == null) {
            return null;
        }

        return CategoryEntity.builder()
                .id(domain.getId() != null ? domain.getId().id() : null)
                .groupCode(domain.getGroupCode())
                .value(domain.getValue())
                .description(domain.getDescription())
                .level(domain.getLevel())
                .sortOrder(domain.getSortOrder())
                .isActive(domain.getIsActive())
                .build();
    }

    public List<Category> toDomainList(List<CategoryEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }

        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public List<CategoryEntity> toEntityList(List<Category> domains) {
        if (domains == null || domains.isEmpty()) {
            return List.of();
        }

        return domains.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
