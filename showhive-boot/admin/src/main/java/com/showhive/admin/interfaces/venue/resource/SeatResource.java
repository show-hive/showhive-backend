package com.showhive.admin.interfaces.venue.resource;

import com.showhive.admin.application.command.dto.venue.SeatDto;
import com.showhive.admin.application.command.usecase.venue.seat.CreateSeatUseCase;
import com.showhive.admin.interfaces.venue.dto.SeatRequest;
import com.showhive.admin.interfaces.venue.dto.SeatResponse;
import com.showhive.auth.RequireRole;
import com.showhive.member.domain.Role;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequireRole(role = Role.MANAGER)
@RequestMapping("/admin/v1/venues/{venueId}/seats")
public class SeatResource {

    private final CreateSeatUseCase createSeatUseCase;

    //    @Override
    @PostMapping
    public ResponseEntity<SeatResponse> createSeatGrade(@Valid @RequestBody SeatRequest seatRequest,
                                                        @PathVariable long venueId) {
        SeatDto seatDto = SeatDto.of(seatRequest);
        SeatResponse seatResponse = createSeatUseCase.create(seatDto, venueId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(seatResponse);
    }
}
