package com.showhive.venue;

import com.showhive.venue.domain.Venue;
import com.showhive.venue.entity.VenueEntity;
import com.showhive.venue.exception.VenueErrorCode;
import com.showhive.venue.exception.VenueException;
import com.showhive.venue.mapper.VenueMapper;
import com.showhive.venue.query.VenueQueryJpaRepository;
import com.showhive.venue.repository.query.VenueQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VenueQueryRepositoryImpl implements VenueQueryRepository {

    private final VenueQueryJpaRepository venueQueryJpaRepository;
    private final VenueMapper venueMapper;

    @Override
    public Venue findById(long venueId) {
        VenueEntity venueEntity = venueQueryJpaRepository.findById(venueId)
                .orElseThrow(() -> new VenueException(VenueErrorCode.VENUE_NOT_FOUND));

        return venueMapper.toDomain(venueEntity);
    }

    @Override
    public boolean existsByName(String name) {
        return venueQueryJpaRepository.existsByName(name);
    }
}
