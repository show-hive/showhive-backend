package com.showhive.category.repository.command;

import com.showhive.category.domain.Category;

public interface CategoryCommandRepository {
    Category saveCategory(Category category);
}
