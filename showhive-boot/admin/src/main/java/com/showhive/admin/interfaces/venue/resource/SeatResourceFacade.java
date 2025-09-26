package com.showhive.admin.interfaces.venue.resource;

import com.showhive.admin.interfaces.venue.dto.SeatRequest;
import com.showhive.admin.interfaces.venue.dto.SeatResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Seat API")
@SecurityRequirement(name = "Authorization")
public interface SeatResourceFacade {

    @Operation(
            summary = "좌석 생성",
            requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = SeatRequest.class))),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "좌석 생성 성공",
                            content = @Content(schema = @Schema(implementation = SeatResponse.class)))
            }
    )
    ResponseEntity<SeatResponse> createSeat(
            SeatRequest seatRequest,
            @Parameter(name = "venueId", description = "공연장 ID", example = "1") long venueId
    );
}
