package com.showhive.admin.interfaces.venue.dto;

import com.showhive.venue.domain.Venue;

public record VenueResponse(
        long venueId,
        String name
) {
    public VenueResponse(Venue venue) {
        this(venue.getId(), venue.getName());
    }
}
