package com.showhive.venue;

import com.showhive.venue.command.VenueCommandJpaRepository;
import com.showhive.venue.domain.Venue;
import com.showhive.venue.entity.VenueEntity;
import com.showhive.venue.mapper.VenueMapper;
import com.showhive.venue.repository.command.VenueCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VenueCommandRepositoryImpl implements VenueCommandRepository {

    private final VenueCommandJpaRepository venueCommandJpaRepository;
    private final VenueMapper venueMapper;

    @Override
    public Venue create(Venue venue) {
        VenueEntity entity = venueMapper.toEntity(venue);
        venueCommandJpaRepository.save(entity);
        return venueMapper.toDomain(entity);
    }
}
