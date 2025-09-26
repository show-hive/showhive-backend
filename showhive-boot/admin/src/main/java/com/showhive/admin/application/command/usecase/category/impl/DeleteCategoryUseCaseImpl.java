package com.showhive.admin.application.command.usecase.category.impl;

import com.showhive.admin.application.command.usecase.category.DeleteCategoryUseCase;
import com.showhive.category.repository.command.CategoryCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCategoryUseCaseImpl implements DeleteCategoryUseCase {
    private CategoryCommandRepository commandRepository;

    @Override
    public void handle(Long id) {
        commandRepository.deActiveCategory(id);
    }
}
