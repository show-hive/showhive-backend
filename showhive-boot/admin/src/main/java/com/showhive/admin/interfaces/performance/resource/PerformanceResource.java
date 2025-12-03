package com.showhive.admin.interfaces.performance.resource;

import com.showhive.admin.application.command.dto.CreatePerformanceDto;
import com.showhive.admin.application.command.usecase.performance.CreatePerformanceUseCase;
import com.showhive.admin.application.command.usecase.performance.CreateSessionUseCase;
import com.showhive.admin.interfaces.category.dto.CreateSessionRequest;
import com.showhive.admin.interfaces.performance.dto.CreatePerformanceRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@RequestMapping("/admin/v1/performance")
@Controller
@RequiredArgsConstructor
public class PerformanceResource {
    private final CreatePerformanceUseCase createPerformanceUseCase;
    private final CreateSessionUseCase createSessionUsecase;

    @PostMapping
    public void createPerformance(@Valid @RequestBody CreatePerformanceRequest createRequest) {
        CreatePerformanceDto commandDto = CreatePerformanceDto.of(createRequest);

        createPerformanceUseCase.handle(commandDto);
    }


    @PostMapping("/{performanceId}/sessions")
    public ResponseEntity<Void> addSession(
            @PathVariable Long performanceId,
            @RequestBody @Valid CreateSessionRequest request) {

        createSessionUsecase.handle(performanceId, request);
        return ResponseEntity.ok().build();
    }
}
