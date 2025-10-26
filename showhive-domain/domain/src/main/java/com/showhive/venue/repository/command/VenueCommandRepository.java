package com.showhive.venue.repository.command;

import com.showhive.venue.entity.Venue;

public interface VenueCommandRepository {
    Venue create(Venue venue);
}
