package com.showhive.admin.application.command.dto.venue;

import com.showhive.admin.interfaces.venue.dto.VenueRequest;

public record VenueDto(

        String name,

        String address,

        Double latitude,

        Double longitude,

        String contactNumber,

        String link
) {
    public static VenueDto of(VenueRequest venueRequest) {
        return new VenueDto(
                venueRequest.name(),
                venueRequest.address(),
                venueRequest.latitude(),
                venueRequest.longitude(),
                venueRequest.contactNumber(),
                venueRequest.link()
        );
    }
}
