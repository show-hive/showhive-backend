package com.showhive.venue;

import com.showhive.venue.command.VenueCommandJpaRepository;
import com.showhive.venue.domain.Venue;
import com.showhive.venue.repository.command.VenueCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SeatQueryRepositoryImpl implements VenueCommandRepository {

    private VenueCommandJpaRepository venueCommandJpaRepository;

    @Override
    public Venue create(Venue venue) {
        return venueCommandJpaRepository.save(venue);
    }
}
