package com.showhive.venue.repository.query;

import com.showhive.venue.entity.Venue;
import java.util.Optional;

public interface VenueQueryRepository {

    Optional<Venue> findById(long venueId);

    boolean existsByName(String name);
}
