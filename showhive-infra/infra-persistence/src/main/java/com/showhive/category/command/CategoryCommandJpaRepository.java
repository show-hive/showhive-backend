package com.showhive.category.command;

import com.showhive.category.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryCommandJpaRepository extends JpaRepository<CategoryEntity, Long> {
}
