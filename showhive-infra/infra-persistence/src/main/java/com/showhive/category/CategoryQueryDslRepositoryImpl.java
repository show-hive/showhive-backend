package com.showhive.category;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.showhive.category.domain.Category;
import com.showhive.category.query.CategoryQueryDslRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryQueryDslRepositoryImpl implements CategoryQueryDslRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Category> findActiveWithChildrenById(Long parentId) {
        return List.of();
    }
}
