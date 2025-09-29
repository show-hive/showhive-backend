package com.showhive.admin.application.command.usecase.venue;


import com.showhive.admin.interfaces.venue.dto.VenueResponse;
import com.showhive.venue.domain.Venue;
import com.showhive.venue.exception.VenueErrorCode;
import com.showhive.venue.exception.VenueException;
import com.showhive.venue.repository.query.VenueQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadVenueUseCaseImpl implements ReadVenueUseCase {

    private final VenueQueryRepository venueQueryRepository;

    @Override
    public VenueResponse handle(long venueId) {
        Venue venue = venueQueryRepository.findById(venueId)
                .orElseThrow(() -> new VenueException(VenueErrorCode.VENUE_NOT_FOUND));
        return new VenueResponse(venue);
    }
}
