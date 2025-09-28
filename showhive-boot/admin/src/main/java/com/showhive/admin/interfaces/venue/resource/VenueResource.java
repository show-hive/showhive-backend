package com.showhive.admin.interfaces.venue.resource;

import com.showhive.admin.application.command.dto.venue.VenueDto;
import com.showhive.admin.application.command.usecase.venue.CreateVenueUseCase;
import com.showhive.admin.application.command.usecase.venue.ReadVenueUseCase;
import com.showhive.admin.interfaces.venue.dto.VenueRequest;
import com.showhive.admin.interfaces.venue.dto.VenueResponse;
import com.showhive.auth.RequireRole;
import com.showhive.member.domain.Role;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequireRole(role = Role.MANAGER)
@RequestMapping("/admin/v1/venues")
public class VenueResource implements VenueResourceFacade {

    private final CreateVenueUseCase createVenueUseCase;
    private final ReadVenueUseCase readVenueUseCase;

    @Override
    @PostMapping
    public ResponseEntity<VenueResponse> createVenue(@Valid @RequestBody VenueRequest venueRequest) {
        VenueDto venueDto = VenueDto.of(venueRequest);
        VenueResponse venueResponse = createVenueUseCase.create(venueDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(venueResponse);
    }

    @Override
    @GetMapping("/{venueId}")
    public ResponseEntity<VenueResponse> readVenue(@PathVariable long venueId) {
        VenueResponse response = readVenueUseCase.read(venueId);
        return ResponseEntity.ok(response);
    }
}
