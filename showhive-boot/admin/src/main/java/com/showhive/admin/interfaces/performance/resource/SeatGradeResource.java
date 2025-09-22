package com.showhive.admin.interfaces.performance.resource;

import com.showhive.admin.application.command.dto.SeatGradeDto;
import com.showhive.admin.application.command.usecase.seatgrade.CreateSeatGradeUseCase;
import com.showhive.admin.application.command.usecase.seatgrade.DeleteSeatGradeUseCase;
import com.showhive.admin.application.command.usecase.seatgrade.ReadSeatGradeUseCase;
import com.showhive.admin.application.command.usecase.seatgrade.UpdateSeatGradeUseCase;
import com.showhive.admin.interfaces.performance.dto.SeatGradeListResponse;
import com.showhive.admin.interfaces.performance.dto.SeatGradeRequest;
import com.showhive.admin.interfaces.performance.dto.SeatGradeResponse;
import com.showhive.auth.RequireRole;
import com.showhive.member.domain.Role;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequireRole(role = Role.MANAGER)
@RequestMapping("/admin/v1/seat-grade")
public class SeatGradeResource {

    private final CreateSeatGradeUseCase createSeatGradeUseCase;
    private final ReadSeatGradeUseCase readSeatGradeUseCase;
    private final UpdateSeatGradeUseCase updateSeatGradeUseCase;
    private final DeleteSeatGradeUseCase deleteSeatGradeUseCase;

    @PostMapping
    public ResponseEntity<Void> createSeatGrade(@Valid @RequestBody SeatGradeRequest createRequest) {
        SeatGradeDto commandDto = SeatGradeDto.of(createRequest);
        createSeatGradeUseCase.handle(commandDto);
        return ResponseEntity.ok()
                .build();
    }

    @GetMapping
    public ResponseEntity<SeatGradeListResponse> readAllSeatGrades() {
        SeatGradeListResponse response = readSeatGradeUseCase.readAllSeatGrades();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{seatGradeId}")
    public ResponseEntity<SeatGradeResponse> readSeatGrade(@PathVariable long seatGradeId) {
        SeatGradeResponse response = readSeatGradeUseCase.readSeatGrade(seatGradeId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{seatGradeId}")
    public ResponseEntity<Void> updatePerformance(@PathVariable long seatGradeId,
                                                  @Valid @RequestBody SeatGradeRequest updateRequest) {
        SeatGradeDto commandDto = SeatGradeDto.of(updateRequest);
        updateSeatGradeUseCase.update(seatGradeId, commandDto);
        return ResponseEntity.ok()
                .build();
    }

    @DeleteMapping("/{seatGradeId}")
    public ResponseEntity<Void> deleteSeatGrade(@PathVariable long seatGradeId) {
        deleteSeatGradeUseCase.deleteSeatGrade(seatGradeId);
        return ResponseEntity.noContent()
                .build();
    }
}
