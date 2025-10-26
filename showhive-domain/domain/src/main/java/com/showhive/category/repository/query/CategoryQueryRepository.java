package com.showhive.category.repository.query;

import com.showhive.category.entity.Category;
import java.util.Optional;

public interface CategoryQueryRepository {
    Optional<Category> findById(Long id);

    List<Category> findByIds(List<Long> categoryIds);

    boolean existsCategory(String groupCode, String value);

    Optional<Category> findCategoryByGroupCodeAndValue(String groupCode, String value);

    Optional<Category> findByIdWithChildren(Long id);

    Optional<Category> findByValueWithChildren(String value);

}
