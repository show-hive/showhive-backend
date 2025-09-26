package com.showhive.admin.application.command.usecase.venue.seat;

import com.showhive.admin.application.command.dto.venue.SeatDto;
import com.showhive.admin.interfaces.venue.dto.SeatResponse;

public interface CreateSeatUseCase {
    SeatResponse create(SeatDto seatDto, long venueId);
}
