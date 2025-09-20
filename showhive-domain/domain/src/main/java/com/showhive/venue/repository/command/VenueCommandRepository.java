package com.showhive.venue.repository.command;

import com.showhive.venue.domain.Venue;

public interface VenueCommandRepository {
    Venue create(Venue venue);
}
