package com.showhive.category.domain;

import com.showhive.BaseEntity;
import com.showhive.category.exception.CategoryErrorCode;
import com.showhive.category.exception.CategoryException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "categories")
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String groupCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @Column(name = "code")
    private String value;

    private String description;

    private Short level;

    private Short sortOrder;

    private Boolean isActive;

    @Builder.Default
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Category> children = new ArrayList<>();

    public static Category createRoot(String groupCode, String value, String description, Short sortOrder, Boolean isActive) {
        return Category.builder()
                .groupCode(groupCode)
                .value(value)
                .description(description)
                .sortOrder(sortOrder)
                .level((short) 0)
                .isActive(isActive)
                .build();
    }

    public static Category createNodeCategory(String groupCode, Category parent, String value, String description, Short sortOrder, Boolean isActive) {
        if(parent == null) {
            throw new CategoryException(CategoryErrorCode.CATEGORY_PARENT_NOT_FOUND);
        }

        return Category.builder()
                .groupCode(groupCode)
                .parent(parent)
                .value(value)
                .description(description)
                .level((short) (parent.getLevel() + 1))
                .sortOrder(sortOrder)
                .isActive(isActive)
                .build();
    }
}
