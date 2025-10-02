package com.showhive.admin.interfaces.venue.resource;

import com.showhive.admin.application.command.dto.venue.SeatGradeDto;
import com.showhive.admin.application.command.usecase.venue.seatgrade.CreateSeatGradeUseCase;
import com.showhive.admin.application.command.usecase.venue.seatgrade.DeleteSeatGradeUseCase;
import com.showhive.admin.application.command.usecase.venue.seatgrade.ReadSeatGradeUseCase;
import com.showhive.admin.application.command.usecase.venue.seatgrade.UpdateSeatGradeUseCase;
import com.showhive.admin.interfaces.venue.dto.SeatGradeListResponse;
import com.showhive.admin.interfaces.venue.dto.SeatGradeRequest;
import com.showhive.admin.interfaces.venue.dto.SeatGradeResponse;
import com.showhive.auth.RequireRole;
import com.showhive.member.domain.Role;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequireRole(role = Role.MANAGER)
@RequestMapping("/admin/v1/seat-grade")
public class SeatGradeResource implements SeatGradeResourceFacade {

    private final CreateSeatGradeUseCase createSeatGradeUseCase;
    private final ReadSeatGradeUseCase readSeatGradeUseCase;
    private final UpdateSeatGradeUseCase updateSeatGradeUseCase;
    private final DeleteSeatGradeUseCase deleteSeatGradeUseCase;

    @Override
    @PostMapping
    public ResponseEntity<Void> createSeatGrade(@Valid @RequestBody SeatGradeRequest createRequest) {
        SeatGradeDto commandDto = SeatGradeDto.of(createRequest);
        createSeatGradeUseCase.handle(commandDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @Override
    @GetMapping
    public ResponseEntity<SeatGradeListResponse> readAllSeatGrades(@RequestParam(name = "size", defaultValue = "20") int pageSize,
                                                                   @RequestParam(name = "lastGradeId", defaultValue = "0") long lastGradeId,
                                                                   @RequestParam(name = "keyword", required = false) String keyword,
                                                                   @RequestParam(name = "direction", defaultValue = "desc") String direction) {
        SeatGradeListResponse response = readSeatGradeUseCase.readAllSeatGrades(
                pageSize, lastGradeId, keyword, direction);
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/{seatGradeId}")
    public ResponseEntity<SeatGradeResponse> readSeatGrade(@PathVariable long seatGradeId) {
        SeatGradeResponse response = readSeatGradeUseCase.readSeatGrade(seatGradeId);
        return ResponseEntity.ok(response);
    }

    @Override
    @PutMapping("/{seatGradeId}")
    public ResponseEntity<Void> updatePerformance(@PathVariable long seatGradeId,
                                                  @Valid @RequestBody SeatGradeRequest updateRequest) {
        SeatGradeDto commandDto = SeatGradeDto.of(updateRequest);
        updateSeatGradeUseCase.handle(seatGradeId, commandDto);
        return ResponseEntity.ok()
                .build();
    }

    @Override
    @DeleteMapping("/{seatGradeId}")
    public ResponseEntity<Void> deleteSeatGrade(@PathVariable long seatGradeId) {
        deleteSeatGradeUseCase.handle(seatGradeId);
        return ResponseEntity.noContent()
                .build();
    }
}
