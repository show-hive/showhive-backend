package com.showhive.admin.application.command.usecase.venue;


import com.showhive.admin.application.command.dto.venue.VenueResult;
import com.showhive.venue.entity.Venue;
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
    public VenueResult handle(long venueId) {
        Venue venue = venueQueryRepository.findById(venueId)
                .orElseThrow(() -> new VenueException(VenueErrorCode.VENUE_NOT_FOUND));
        return VenueResult.from(venue);
    }
}
