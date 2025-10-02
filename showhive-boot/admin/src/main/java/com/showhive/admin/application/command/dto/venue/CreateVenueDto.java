package com.showhive.admin.application.command.dto.venue;

import com.showhive.admin.interfaces.venue.dto.CreateVenueRequest;

public record CreateVenueDto(
        String name,

        String address,

        Double latitude,

        Double longitude,

        String contactNumber,

        String link
) {
    public static CreateVenueDto of(CreateVenueRequest createVenueRequest) {
        return new CreateVenueDto(
                createVenueRequest.name(),
                createVenueRequest.address(),
                createVenueRequest.latitude(),
                createVenueRequest.longitude(),
                createVenueRequest.contactNumber(),
                createVenueRequest.link()
        );
    }
}
