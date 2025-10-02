package com.showhive.admin.application.command.usecase.venue;

import com.showhive.admin.application.command.dto.venue.CreateVenueDto;
import com.showhive.admin.application.command.dto.venue.VenueResult;

public interface CreateVenueUseCase {

    VenueResult handle(CreateVenueDto createVenueDto);
}
