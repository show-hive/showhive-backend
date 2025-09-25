package com.showhive.category.query;

import com.showhive.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryQueryJpaRepository extends JpaRepository<Category, Long> {
    boolean existsByValue(String value);
}
