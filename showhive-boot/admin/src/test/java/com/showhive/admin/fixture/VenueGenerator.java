package com.showhive.admin.fixture;

import com.showhive.venue.domain.Venue;
import com.showhive.venue.repository.command.VenueCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VenueGenerator {

    @Autowired
    private VenueCommandRepository venueCommandRepository;

    //TODO: venue 인자 추가 필요
    public Venue generateVenue() {
        Venue venue = Venue.create();
        return venueCommandRepository.create(venue);
    }
}
