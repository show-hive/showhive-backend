package com.showhive.admin.application.command.usecase;

import com.showhive.admin.application.command.dto.CreateSeatGradeDto;

public interface CreateSeatGradeUseCase {
    void handle(CreateSeatGradeDto commandDto);
}
