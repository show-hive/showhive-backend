package com.showhive.admin.application.command.usecase.venue;

import com.showhive.admin.application.command.dto.venue.CreateVenueDto;
import com.showhive.admin.interfaces.venue.dto.VenueResponse;

public interface CreateVenueUseCase {

    VenueResponse handle(CreateVenueDto createVenueDto);
}
