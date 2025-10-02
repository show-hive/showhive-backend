package com.showhive.admin.interfaces.category.resource;

import com.showhive.admin.application.command.dto.category.CreateCategoryDto;
import com.showhive.admin.application.command.dto.category.DetailCategoryResult;
import com.showhive.admin.application.command.dto.category.UpdateCategoryDto;
import com.showhive.admin.application.command.usecase.category.CreateCategoryUseCase;
import com.showhive.admin.application.command.usecase.category.DeleteCategoryUseCase;
import com.showhive.admin.application.command.usecase.category.UpdateCategoryUseCase;
import com.showhive.admin.application.command.usecase.category.DetailCategoryUseCase;
import com.showhive.admin.interfaces.category.dto.CreateCategoryRequest;
import com.showhive.admin.interfaces.category.dto.UpdateCategoryRequest;
import com.showhive.admin.interfaces.category.dto.DetailCategoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequestMapping("/admin/v1/categories")
@RestController
@RequiredArgsConstructor
public class CategoryResource implements CategoryFacade {
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final CreateCategoryUseCase createCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DetailCategoryUseCase detailCategoryUseCase;

    @Override
    @PostMapping
    public void create(@Valid @RequestBody CreateCategoryRequest categoryRequest) {
        CreateCategoryDto createCategoryDto = CreateCategoryDto.of(categoryRequest);
        createCategoryUseCase.handle(createCategoryDto);
    }

    @Override
    @PutMapping("/{categoryId}")
    public void update(Long categoryId, UpdateCategoryRequest updateRequest) {
        UpdateCategoryDto updateCategoryDto = UpdateCategoryDto.of(updateRequest);

        updateCategoryUseCase.handle(categoryId, updateCategoryDto);
    }

    @Override
    @DeleteMapping("/{categoryId}")
    public void delete(@PathVariable Long categoryId) {
        deleteCategoryUseCase.handle(categoryId);
    }

    @GetMapping("/{categoryId}")
    public DetailCategoryResponse detail(@PathVariable Long categoryId) {
        DetailCategoryResult result = detailCategoryUseCase.handle(categoryId);
        return DetailCategoryResponse.from(result);
    }
}
