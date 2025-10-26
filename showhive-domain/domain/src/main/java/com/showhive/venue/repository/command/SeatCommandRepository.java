package com.showhive.venue.repository.command;

import com.showhive.venue.entity.Seat;

public interface SeatCommandRepository {
    Seat create(Seat seat);
}
