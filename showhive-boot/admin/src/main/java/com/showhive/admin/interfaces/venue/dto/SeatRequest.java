package com.showhive.admin.interfaces.venue.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SeatRequest(

        @NotNull
        long seatGradeId,

        String seatColumn,

        Short seatRow,

        Short seatFloor,

        @NotBlank
        String seatType
) {
}
