package com.showhive.admin.application.command.usecase;

import com.showhive.admin.application.command.dto.CreatePerformanceDto;

public interface CreatePerformanceUseCase {

    void handle(CreatePerformanceDto commandDto);
}
