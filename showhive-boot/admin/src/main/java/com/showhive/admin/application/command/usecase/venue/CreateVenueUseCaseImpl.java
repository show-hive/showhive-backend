package com.showhive.admin.application.command.usecase.venue;


import com.showhive.admin.application.command.dto.venue.VenueDto;
import com.showhive.admin.interfaces.venue.dto.VenueResponse;
import com.showhive.venue.domain.Venue;
import com.showhive.venue.exception.VenueErrorCode;
import com.showhive.venue.exception.VenueException;
import com.showhive.venue.repository.command.VenueCommandRepository;
import com.showhive.venue.repository.query.VenueQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateVenueUseCaseImpl implements CreateVenueUseCase {

    private final VenueCommandRepository venueCommandRepository;
    private final VenueQueryRepository venueQueryRepository;

    @Override
    public VenueResponse create(VenueDto venueDto) {
        if (venueQueryRepository.existsByName(venueDto.name())) {
            throw new VenueException(VenueErrorCode.VENUE_ALREADY_EXISTS);
        }

        Venue venue = venueCommandRepository.create(
                Venue.create(venueDto.name(), venueDto.address(),
                        venueDto.latitude(), venueDto.longitude(),
                        venueDto.contactNumber(), venueDto.link())
        );
        return new VenueResponse(venue);
    }
}
