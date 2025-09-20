package com.showhive.admin.application.command.usecase.venue;

import com.showhive.admin.application.command.dto.CreateSeatGradeDto;

public interface ListSeatGradeUseCase {
    void handle(CreateSeatGradeDto commandDto);
}
