package com.showhive.admin.interfaces.venue.dto;

import com.showhive.admin.application.command.dto.venue.VenueResult;
import com.showhive.venue.domain.Seat;

public record SeatResponse(
        long seatId,
        VenueResponse venueResponse,
        SeatGradeResponse seatGradeResponse,
        String seatColumn,
        Short seatRow,
        Short seatFloor,
        String seatType
) {
    public SeatResponse(Seat seat) {
        this(seat.getId(),
                //TODO: seat 관련 dto 수정 필요(서비스 레이어 용 dto 만들기)
                VenueResponse.from(VenueResult.from(seat.getVenue())),
                new SeatGradeResponse(seat.getSeatGrade()),
                seat.getSeatColumn(), seat.getSeatRow(),
                seat.getSeatFloor(), seat.getSeatType().getDescription());
    }
}
