package com.showhive.venue.repository.query;

import com.showhive.venue.domain.Venue;

public interface VenueQueryRepository {

    Venue findById(long venueId);

    boolean existsByName(String name);
}
