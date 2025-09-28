package com.showhive.admin.interfaces.venue.resource;

import com.showhive.admin.interfaces.venue.dto.VenueRequest;
import com.showhive.admin.interfaces.venue.dto.VenueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Venue API")
@SecurityRequirement(name = "Authorization")
public interface VenueResourceFacade {

    @Operation(
            summary = "공연장 생성",
            requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = VenueRequest.class))),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "공연장 생성 성공",
                            content = @Content(schema = @Schema(implementation = VenueResponse.class)))
            }
    )
    ResponseEntity<VenueResponse> createVenue(VenueRequest venueRequest);

    @Operation(
            summary = "특정 공연장 조회",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "공연 조회 성공",
                            content = @Content(schema = @Schema(implementation = VenueResponse.class)))
            }
    )
    ResponseEntity<VenueResponse> readVenue(
            @Parameter(name = "venueId", description = "공연장 ID", example = "1") long venueId
    );
}
