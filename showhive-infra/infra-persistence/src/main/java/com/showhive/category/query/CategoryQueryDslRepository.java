package com.showhive.category.query;

import com.showhive.category.entity.Category;
import java.util.List;

public interface CategoryQueryDslRepository {
    List<Category> findActiveWithChildrenById(Long parentId);
}
