package com.showhive.admin.application.command.usecase.venue;

import com.showhive.admin.interfaces.venue.dto.VenueResponse;

public interface ReadVenueUseCase {

    VenueResponse read(long venueId);
}
