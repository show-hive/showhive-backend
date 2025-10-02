package com.showhive.venue;

import com.showhive.venue.domain.Venue;
import com.showhive.venue.query.VenueQueryJpaRepository;
import com.showhive.venue.repository.query.VenueQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VenueQueryRepositoryImpl implements VenueQueryRepository {

    private final VenueQueryJpaRepository venueQueryJpaRepository;

    @Override
    public Optional<Venue> findById(long venueId) {
        return venueQueryJpaRepository.findById(venueId);
    }

    @Override
    public boolean existsByName(String name) {
        return venueQueryJpaRepository.existsByName(name);
    }
}
