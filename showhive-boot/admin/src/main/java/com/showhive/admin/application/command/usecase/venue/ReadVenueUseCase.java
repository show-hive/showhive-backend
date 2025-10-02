package com.showhive.admin.application.command.usecase.venue;

import com.showhive.admin.application.command.dto.venue.VenueResult;

public interface ReadVenueUseCase {

    VenueResult handle(long venueId);
}
