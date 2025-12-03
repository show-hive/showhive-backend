package com.showhive.admin.interfaces.venue.dto;

import com.showhive.admin.application.command.dto.venue.VenueResult;
import com.showhive.venue.domain.Seat;
import com.showhive.venue.domain.Venue;

public record SeatResponse(
        long seatId,
        VenueResponse venueResponse,
        SeatGradeResponse seatGradeResponse,
        String seatColumn,
        Short seatRow,
        Short seatFloor,
        String seatType
) {
    public SeatResponse(Seat seat, Venue venue) {
        this(seat.getId().getSeatId(),
                //TODO: seat 관련 dto 수정 필요(서비스 레이어 용 dto 만들기)
                VenueResponse.from(VenueResult.from(venue)),
                new SeatGradeResponse(seat.getGrade()),
                seat.getColumn(), seat.getRow(),
                seat.getFloor(), seat.getType().getDescription());
    }
}
