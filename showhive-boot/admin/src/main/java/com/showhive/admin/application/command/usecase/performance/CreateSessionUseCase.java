package com.showhive.admin.application.command.usecase.performance;

import com.showhive.admin.interfaces.category.dto.CreateSessionRequest;

public interface CreateSessionUseCase {
    void handle(Long performanceId, CreateSessionRequest request);
}
