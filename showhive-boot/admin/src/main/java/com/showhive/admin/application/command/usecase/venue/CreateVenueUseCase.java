package com.showhive.admin.application.command.usecase.venue;

import com.showhive.admin.application.command.dto.venue.VenueDto;
import com.showhive.admin.interfaces.venue.dto.VenueResponse;

public interface CreateVenueUseCase {

    VenueResponse create(VenueDto venueDto);
}
