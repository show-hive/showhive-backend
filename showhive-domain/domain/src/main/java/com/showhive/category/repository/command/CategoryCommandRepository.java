package com.showhive.category.repository.command;

import com.showhive.category.domain.Category;

public interface CategoryCommandRepository {
    Category createCategory(Category category);
    void deActiveCategory(Long id);
}
