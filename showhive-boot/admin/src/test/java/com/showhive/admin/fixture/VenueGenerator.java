package com.showhive.admin.fixture;

import com.showhive.venue.entity.Venue;
import com.showhive.venue.repository.command.VenueCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VenueGenerator {

    @Autowired
    private VenueCommandRepository venueCommandRepository;

    public Venue generateVenue(String name) {
        Venue venue = Venue.create(name, null, null, null, null, "link.com");
        return venueCommandRepository.create(venue);
    }
}
