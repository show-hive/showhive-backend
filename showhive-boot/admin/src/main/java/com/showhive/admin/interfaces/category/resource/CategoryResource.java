package com.showhive.admin.interfaces.category.resource;

import com.showhive.admin.application.command.dto.category.CreateCategoryDto;
import com.showhive.admin.application.command.usecase.category.CreateCategoryUseCase;
import com.showhive.admin.interfaces.category.dto.CreateCategoryRequest;
import com.showhive.auth.RequireRole;
import com.showhive.member.domain.Role;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RequestMapping("/admin/v1/category")
@RestController
@RequiredArgsConstructor
public class CategoryResource implements CategoryFacade {
    private CreateCategoryUseCase createCategoryUseCase;

    @Override
    @PostMapping
    @RequireRole(role = Role.ADMIN)
    public void create(@Valid @RequestBody CreateCategoryRequest categoryRequest) {

        CreateCategoryDto  createCategoryDto = CreateCategoryDto.of(categoryRequest);
        createCategoryUseCase.handle(createCategoryDto);
    }
}
