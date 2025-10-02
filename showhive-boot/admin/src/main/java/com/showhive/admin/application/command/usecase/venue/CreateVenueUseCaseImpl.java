package com.showhive.admin.application.command.usecase.venue;


import com.showhive.admin.application.command.dto.venue.CreateVenueDto;
import com.showhive.admin.application.command.dto.venue.VenueResult;
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
    public VenueResult handle(CreateVenueDto createVenueDto) {
        if (venueQueryRepository.existsByName(createVenueDto.name())) {
            throw new VenueException(VenueErrorCode.VENUE_ALREADY_EXISTS);
        }

        Venue venue = venueCommandRepository.create(
                Venue.create(createVenueDto.name(), createVenueDto.address(),
                        createVenueDto.latitude(), createVenueDto.longitude(),
                        createVenueDto.contactNumber(), createVenueDto.link())
        );
        return VenueResult.from(venue);
    }
}
