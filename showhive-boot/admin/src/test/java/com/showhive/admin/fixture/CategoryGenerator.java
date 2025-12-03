package com.showhive.admin.fixture;

import com.showhive.category.domain.Category;
import com.showhive.category.exception.CategoryErrorCode;
import com.showhive.category.exception.CategoryException;
import com.showhive.category.repository.command.CategoryCommandRepository;
import com.showhive.category.repository.query.CategoryQueryRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryGenerator {
    private static final String GROUP_CODE = "MENU";
    private static final boolean IS_ACTIVE = true;

    @Autowired
    private CategoryCommandRepository commandRepository;
    @Autowired
    private CategoryQueryRepository queryRepository;

    public Category generateNodeCategory(String parentCode, String value, String description) {
        generateRootCategory();
        Category parent;
        if (parentCode != null && !parentCode.isBlank()) {
            parent = queryRepository.findByValueWithChildren(parentCode)
                    .orElseThrow(() -> new CategoryException(CategoryErrorCode.CATEGORY_PARENT_NOT_FOUND));
        } else {
            parent = generateNodeCategory(value);
        }

        int size = parent != null && parent.getChildren() != null ? parent.getChildren().size() : 0;
        short sortOrder = (short) (size + 1);
        Category category = Category.createNodeCategory(GROUP_CODE, parent, value, description, sortOrder, IS_ACTIVE);

        return commandRepository.saveCategory(category);
    }

    public Category generateNodeCategory(String value) {
        generateRootCategory();
        Optional<Category> rootCategoryOptional = queryRepository.findCategoryByGroupCodeAndValue(GROUP_CODE, "ticket");

        if (rootCategoryOptional.isEmpty()) {
            return null;
        }

        Category rootCategory = rootCategoryOptional.get();

        Category category = Category.createNodeCategory(GROUP_CODE, rootCategory, value,
                "테스트 설명", (short) rootCategory.getChildren().size(), IS_ACTIVE);

        return commandRepository.saveCategory(category);
    }

    public List<Category> generateRootCategory() {
        Map<String, String> menu = Map.of(
                "home", "홈",
                "tour", "투어",
                "ticket", "티켓"
        );

        AtomicInteger sortOrder = new AtomicInteger(0);

        List<Category> categories = new ArrayList<>();
        menu.forEach((categoryName, categoryValue) -> {
            Category category = Category.createRoot(
                    GROUP_CODE,
                    categoryName,
                    categoryValue,
                    (short) sortOrder.getAndIncrement(),
                    IS_ACTIVE
            );
            Category savedCategory = commandRepository.saveCategory(category);
            categories.add(savedCategory);
        });

        return categories;
    }
}
