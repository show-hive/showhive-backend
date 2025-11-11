package com.showhive.venue.repository.command;

import com.showhive.venue.entity.SeatEntity;

public interface SeatCommandRepository {
    SeatEntity create(SeatEntity seat);
}
