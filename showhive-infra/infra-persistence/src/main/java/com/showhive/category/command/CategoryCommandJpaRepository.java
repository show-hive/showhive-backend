package com.showhive.category.command;

import com.showhive.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryCommandJpaRepository extends JpaRepository<Category, Long> {
}
