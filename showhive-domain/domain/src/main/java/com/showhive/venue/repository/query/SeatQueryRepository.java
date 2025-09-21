package com.showhive.venue.repository.query;

import com.showhive.venue.domain.Seat;

public interface SeatQueryRepository {
    Seat create(Seat seat);
}
