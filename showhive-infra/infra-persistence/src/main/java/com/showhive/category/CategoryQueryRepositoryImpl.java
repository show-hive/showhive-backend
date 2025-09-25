package com.showhive.category;

import com.showhive.category.domain.Category;
import com.showhive.category.query.CategoryQueryJpaRepository;
import com.showhive.category.repository.query.CategoryQueryRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryQueryRepositoryImpl implements CategoryQueryRepository {
    private final CategoryQueryJpaRepository queryRepository;

    @Override
    public Optional<Category> findById(Long id) {
        return queryRepository.findById(id);
    }

    @Override
    public boolean existsCategory(String value) {
        return queryRepository.existsByValue(value);
    }
}
