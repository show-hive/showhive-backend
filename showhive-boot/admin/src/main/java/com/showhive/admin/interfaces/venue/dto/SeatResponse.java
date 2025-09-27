package com.showhive.admin.interfaces.venue.dto;

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
                new VenueResponse(seat.getVenue()),
                new SeatGradeResponse(seat.getSeatGrade()),
                seat.getSeatColumn(), seat.getSeatRow(),
                seat.getSeatFloor(), seat.getSeatType().getDescription());
    }
}
