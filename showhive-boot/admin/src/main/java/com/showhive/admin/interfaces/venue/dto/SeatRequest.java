package com.showhive.admin.interfaces.venue.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SeatRequest(

        @NotNull
        @Schema(description = "좌석 등급 id", example = "1")
        long seatGradeId,

        @Schema(description = "좌석 열", example = "A", nullable = true)
        String seatColumn,

        @Schema(description = "좌석 행", example = "1", nullable = true)
        Short seatRow,

        @Schema(description = "층", example = "1", nullable = true)
        Short seatFloor,

        @NotBlank
        @Schema(description = "좌석 타입",
                example = "STANDING",
                allowableValues = {"STANDING", "RESERVED"})
        String seatType
) {
}
