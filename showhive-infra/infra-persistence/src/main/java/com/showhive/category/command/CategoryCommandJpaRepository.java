package com.showhive.category.command;

import com.showhive.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryCommandJpaRepository extends JpaRepository<Category, Long> {
}
