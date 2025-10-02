package com.showhive.admin.interfaces.venue.dto;

import com.showhive.admin.application.command.dto.venue.VenueResult;

public record VenueResponse(
        long venueId,
        String name,
        String address,
        Double latitude,
        Double longitude,
        String contactNumber,
        String link
) {
    public static VenueResponse from(VenueResult venueResult) {
        return new VenueResponse(venueResult.venueId(),
                venueResult.name(),
                venueResult.address(),
                venueResult.latitude(),
                venueResult.longitude(),
                venueResult.contactNumber(),
                venueResult.link());
    }
}
