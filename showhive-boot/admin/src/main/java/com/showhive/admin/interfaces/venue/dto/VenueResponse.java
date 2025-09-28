package com.showhive.admin.interfaces.venue.dto;

import com.showhive.venue.domain.Venue;

public record VenueResponse(
        long venueId,
        String name,
        String address,
        Double latitude,
        Double longitude,
        String contactNumber,
        String link
) {
    public VenueResponse(Venue venue) {
        this(venue.getId(), venue.getName(), venue.getAddress(),
                venue.getLatitude(), venue.getLongitude(),
                venue.getContactNumber(), venue.getLink());
    }
}
