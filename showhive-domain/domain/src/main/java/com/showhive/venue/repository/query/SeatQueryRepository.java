package com.showhive.venue.repository.query;

import com.showhive.venue.domain.Seat;

import java.util.Optional;

public interface SeatQueryRepository {

    Optional<Seat> findById(long seatId);
}
