package com.showhive.admin.interfaces.performance.resource;

import com.showhive.admin.application.command.dto.CreateSeatGradeDto;
import com.showhive.admin.application.command.usecase.CreateSeatGradeUseCase;
import com.showhive.admin.interfaces.performance.dto.CreateSeatGradeRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@RequestMapping("/admin/v1/seat-grade")
@Controller
@RequiredArgsConstructor
public class SeatGradeResource {
    private final CreateSeatGradeUseCase createSeatGradeUseCase;

    @PostMapping
    public void createPerformance(@Valid @RequestBody CreateSeatGradeRequest createRequest) {
        CreateSeatGradeDto commandDto = CreateSeatGradeDto.of(createRequest);

        createSeatGradeUseCase.handle(commandDto);
    }
}
