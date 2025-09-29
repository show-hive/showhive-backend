package com.showhive.admin.fixture;

import com.showhive.category.domain.Category;
import com.showhive.category.exception.CategoryErrorCode;
import com.showhive.category.exception.CategoryException;
import com.showhive.category.repository.command.CategoryCommandRepository;
import com.showhive.category.repository.query.CategoryQueryRepository;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryGenerator {

    @Autowired
    private CategoryCommandRepository commandRepository;
    @Autowired
    private CategoryQueryRepository queryRepository;
    private final String GROUP_CODE = "MENU";
    private final boolean isActive = true;

    public Category generateNodeCategory(String parentCode, String value, String description) {
        Category parent = null;
        if(parentCode != null && !parentCode.isBlank()) {
            parent = queryRepository.findByValueWithChildren(parentCode)
                    .orElseThrow(() -> new CategoryException(CategoryErrorCode.CATEGORY_PARENT_NOT_FOUND));
        }

        int size = parent.getChildren() != null ? parent.getChildren().size() : 0;
        short sortOrder = (short) (size + 1);
        Category category = Category.createNodeCategory(GROUP_CODE, parent, value, description,  sortOrder, isActive);

        return commandRepository.createCategory(category);
    }

    public void generateRootCategory() {
        Map<String, String> menu = Map.of(
                "home", "홈",
                "tour", "투어",
                "ticket", "티켓"
        );

        AtomicInteger index = new AtomicInteger(0);

        menu.forEach((categoryName, categoryValue) -> {
            Category category = Category.createRoot(
                    GROUP_CODE,
                    categoryName,
                    categoryValue,
                    (short) (index.getAndIncrement() - 1),
                    isActive
            );
            commandRepository.createCategory(category);
        });
    }
}
