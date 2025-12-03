package com.showhive.admin.application.command.usecase.performance.impl;

import com.showhive.admin.application.command.usecase.performance.CreateSessionUseCase;
import com.showhive.admin.interfaces.category.dto.CreateSessionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateSessionUseCaseImpl implements CreateSessionUseCase {

    @Override
    public void handle(Long performanceId, CreateSessionRequest request) {

    }
}
