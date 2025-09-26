package com.showhive.admin.interfaces.category.resource;

import com.showhive.admin.application.command.dto.category.CreateCategoryDto;
import com.showhive.admin.application.command.dto.category.UpdateCategoryDto;
import com.showhive.admin.application.command.usecase.category.CreateCategoryUseCase;
import com.showhive.admin.application.command.usecase.category.UpdateCategoryUseCase;
import com.showhive.admin.interfaces.category.dto.CreateCategoryRequest;
import com.showhive.admin.interfaces.category.dto.UpdateCategoryRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequestMapping("/admin/v1/category")
@RestController
@RequiredArgsConstructor
public class CategoryResource implements CategoryFacade {
    private final CreateCategoryUseCase createCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;

    @Override
    @PostMapping
    public void create(@Valid @RequestBody CreateCategoryRequest categoryRequest) {

        CreateCategoryDto  createCategoryDto = new CreateCategoryDto(categoryRequest.groupCode(),
                categoryRequest.parentId(), categoryRequest.value(), categoryRequest.description(),
                categoryRequest.level(), categoryRequest.level(), categoryRequest.isActive());

        createCategoryUseCase.handle(createCategoryDto);
    }

    @Override
    @PutMapping("/{id}")
    public void update(Long id, UpdateCategoryRequest updateRequest) {
        UpdateCategoryDto updateCategoryDto = new UpdateCategoryDto(updateRequest.groupCode(),
                updateRequest.parentId(), updateRequest.value(), updateRequest.description(),
                updateRequest.level(), updateRequest.level(), updateRequest.isActive());

        updateCategoryUseCase.handle(id, updateCategoryDto);
    }
}
