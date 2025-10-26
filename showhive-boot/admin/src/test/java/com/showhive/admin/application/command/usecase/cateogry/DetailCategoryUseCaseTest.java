package com.showhive.admin.application.command.usecase.cateogry;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.showhive.admin.application.command.dto.category.DetailCategoryResult;
import com.showhive.admin.application.command.usecase.BaseUseCaseTest;
import com.showhive.admin.application.command.usecase.category.DetailCategoryUseCase;
import com.showhive.admin.fixture.CategoryGenerator;
import com.showhive.category.entity.Category;
import com.showhive.category.exception.CategoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class DetailCategoryUseCaseTest extends BaseUseCaseTest {

    @Autowired
    private CategoryGenerator categoryGenerator;

    @Autowired
    private DetailCategoryUseCase detailCategoryUseCase;

    @BeforeEach
    void setUp() {
        // 카테고리 루트 생성
        categoryGenerator.generateRootCategory();
    }


    @DisplayName("카테고리 상세 조회 성공")
    @Test
    void detail_category() {
        // given
        categoryGenerator.generateNodeCategory("ticket", "musical", "뮤지컬");
        categoryGenerator.generateNodeCategory("ticket", "concert", "콘서트");
        Category sports = categoryGenerator.generateNodeCategory("ticket", "sports", "스포츠");

        // when
        DetailCategoryResult result = detailCategoryUseCase.handle(sports.getId());

        // then
        assertAll(
                () -> assertThat(result.id()).isEqualTo(sports.getId()),
                () -> assertThat(result.value()).isEqualTo("sports"),
                () -> assertThat(result.description()).isEqualTo("스포츠")
        );
    }

    @DisplayName("존재하지 않는 카테고리 조회 시 예외 발생")
    @Test
    void detail_category_not_found() {
        // given
        Long notExistsId = 999999L;

        // when & then
        assertThrows(CategoryException.class, () -> detailCategoryUseCase.handle(notExistsId));
    }
}
