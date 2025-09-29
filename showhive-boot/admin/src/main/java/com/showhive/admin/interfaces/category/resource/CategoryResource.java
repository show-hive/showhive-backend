package com.showhive.admin.interfaces.category.resource;

import com.showhive.admin.application.command.dto.category.CreateCategoryDto;
import com.showhive.admin.application.command.usecase.category.CreateCategoryUseCase;
import com.showhive.admin.application.command.usecase.category.DetailCategoryUseCase;
import com.showhive.admin.application.command.usecase.category.dto.CategoryResult;
import com.showhive.admin.interfaces.category.dto.CreateCategoryRequest;
import com.showhive.admin.interfaces.category.dto.DetailCategoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequestMapping("/admin/v1/category")
@RestController
@RequiredArgsConstructor
public class CategoryResource implements CategoryFacade {
    private final CreateCategoryUseCase createCategoryUseCase;
    private final DetailCategoryUseCase detailCategoryUseCase;

    @Override
    @PostMapping
    public void create(@Valid @RequestBody CreateCategoryRequest categoryRequest) {
        CreateCategoryDto  createCategoryDto = CreateCategoryDto.of(categoryRequest);
        createCategoryUseCase.handle(createCategoryDto);
    }

    @Override
    @GetMapping("/{categoryId}")
    public DetailCategoryResponse detail(@PathVariable Long categoryId) {
        CategoryResult result = detailCategoryUseCase.handle(categoryId);
        return DetailCategoryResponse.from(result);
    }
}
