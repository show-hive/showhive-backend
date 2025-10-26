package com.showhive.admin.application.command.dto.venue;

import com.showhive.venue.entity.Venue;

public record VenueResult(
        long venueId,
        String name,
        String address,
        Double latitude,
        Double longitude,
        String contactNumber,
        String link
) {
    public static VenueResult from(Venue venue) {
        return new VenueResult(venue.getId(),
                venue.getName(),
                venue.getAddress(),
                venue.getLatitude(),
                venue.getLongitude(),
                venue.getContactNumber(),
                venue.getLink());
    }
}
