package com.showhive.category.query;

import com.showhive.category.entity.CategoryEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CategoryQueryJpaRepository extends JpaRepository<CategoryEntity, Long> {
    @EntityGraph(attributePaths = {"children"})
    Optional<CategoryEntity> findWithChildrenById(@Param("id") Long id);

    Optional<CategoryEntity> findByIdAndIsActiveIsTrue(@Param("id") Long id);

    boolean existsByGroupCodeAndValue(String groupCode, String value);

    @EntityGraph(attributePaths = {"children"})
    Optional<CategoryEntity> findWithChildrenByValue(String value);

    List<CategoryEntity> findAllByIdIn(List<Long> ids);

    Optional<CategoryEntity> findOneByGroupCodeAndValue(String groupCode, String value);
}
