package com.showhive.venue.repository.query;

import com.showhive.venue.domain.Seat;
import java.util.List;
import java.util.Optional;

public interface SeatQueryRepository {

    Optional<Seat> findById(long seatId);

    List<Seat> findAllByVenueIdWithSeatGrade(Long venueId);
}
