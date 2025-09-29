package com.showhive.admin.application.command.usecase.venue.seat;

import com.showhive.admin.interfaces.venue.dto.SeatResponse;

public interface ReadSeatUseCase {

    SeatResponse handle(long venueId, long seatId);
}
