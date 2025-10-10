package com.showhive.category.query;

import com.showhive.category.entity.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CategoryQueryJpaRepository extends JpaRepository<Category, Long> {
    @EntityGraph(attributePaths = {"children"})
    Optional<Category> findWithChildrenById(@Param("id") Long id);

    Optional<Category> findByIdAndIsActiveIsTrue(@Param("id") Long id);

    boolean existsByGroupCodeAndValue(String groupCode, String value);

    @EntityGraph(attributePaths = {"children"})
    Optional<Category> findWithChildrenByValue(String value);
}
