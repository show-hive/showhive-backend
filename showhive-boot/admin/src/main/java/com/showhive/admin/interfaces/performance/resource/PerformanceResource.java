package com.showhive.admin.interfaces.performance.resource;

import com.showhive.admin.application.command.dto.CreatePerformanceDto;
import com.showhive.admin.application.command.usecase.performance.CreatePerformanceUseCase;
import com.showhive.admin.interfaces.performance.dto.CreatePerformanceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/v1/performance")
@Controller
@RequiredArgsConstructor
public class PerformanceResource {
    private final CreatePerformanceUseCase createPerformanceUseCase;

    @PostMapping
    public void createPerformance(@RequestBody CreatePerformanceRequest createRequest) {
        CreatePerformanceDto commandDto = CreatePerformanceDto.of(createRequest);

        createPerformanceUseCase.handle(commandDto);
    }
}
