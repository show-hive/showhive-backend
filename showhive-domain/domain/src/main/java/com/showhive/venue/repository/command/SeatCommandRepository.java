package com.showhive.venue.repository.command;

import com.showhive.venue.domain.Seat;

public interface SeatCommandRepository {
    Seat create(Seat seat);
}
