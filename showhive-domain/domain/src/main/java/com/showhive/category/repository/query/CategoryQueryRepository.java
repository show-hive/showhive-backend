package com.showhive.category.repository.query;

import com.showhive.category.domain.Category;
import java.util.Optional;

public interface CategoryQueryRepository {
    Optional<Category> findById(Long id);

    boolean existsCategory(String value);
}
