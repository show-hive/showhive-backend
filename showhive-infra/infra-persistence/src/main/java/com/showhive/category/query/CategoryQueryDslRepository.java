package com.showhive.category.query;

import com.showhive.category.entity.CategoryEntity;
import java.util.List;

public interface CategoryQueryDslRepository {
    List<CategoryEntity> findActiveWithChildrenById(Long parentId);
}
