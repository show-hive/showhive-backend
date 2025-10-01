package com.showhive.admin.interfaces.category.resource;

import com.showhive.admin.application.command.dto.category.CreateCategoryDto;
import com.showhive.admin.application.command.usecase.category.CreateCategoryUseCase;
import com.showhive.admin.application.command.usecase.category.DeleteCategoryUseCase;
import com.showhive.admin.interfaces.category.dto.CreateCategoryRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequestMapping("/admin/v1/categories")
@RestController
@RequiredArgsConstructor
public class CategoryResource implements CategoryFacade {
    private final CreateCategoryUseCase createCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;

    @Override
    @PostMapping
    public void create(@Valid @RequestBody CreateCategoryRequest categoryRequest) {

        CreateCategoryDto createCategoryDto = new CreateCategoryDto(categoryRequest.groupCode(),
                categoryRequest.parentId(), categoryRequest.value(), categoryRequest.description(),
                categoryRequest.level(), categoryRequest.level(), categoryRequest.isActive());

        createCategoryUseCase.handle(createCategoryDto);
    }

    @Override
    @DeleteMapping("/{categoryId}")
    public void delete(@PathVariable Long categoryId) {
        deleteCategoryUseCase.handle(categoryId);
    }
}
