package com.showhive.admin.application.command.dto.venue;

import com.showhive.admin.interfaces.venue.dto.SeatRequest;
import lombok.Builder;

@Builder
public record SeatDto(

        long seatGradeId,
        String seatColumn,
        Short seatRow,
        Short seatFloor,
        String seatType
) {
    public static SeatDto of(SeatRequest request) {
        return SeatDto.builder()
                .seatGradeId(request.seatGradeId())
                .seatColumn(request.seatColumn())
                .seatRow(request.seatRow())
                .seatFloor(request.seatFloor())
                .seatType(request.seatType())
                .build();
    }

}
