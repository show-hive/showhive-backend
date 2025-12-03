package com.showhive.venue;

import com.showhive.venue.domain.Seat;
import com.showhive.venue.entity.SeatEntity;
import com.showhive.venue.mapper.SeatMapper;
import com.showhive.venue.query.SeatQueryJpaRepository;
import com.showhive.venue.repository.query.SeatQueryRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SeatQueryRepositoryImpl implements SeatQueryRepository {

    private final SeatQueryJpaRepository seatQueryJpaRepository;
    private final SeatMapper seatMapper;

    @Override
    public Optional<Seat> findById(long seatId) {
        Optional<SeatEntity> seatEntityOptional = seatQueryJpaRepository.findById(seatId);

        return seatEntityOptional.map(seatMapper::toDomain);
    }

    @Override
    public List<Seat> findAllByVenueIdWithSeatGrade(Long venueId) {
        List<SeatEntity> seatEntities = seatQueryJpaRepository.findByVenue_Id(venueId);

        return seatEntities.stream().map(seatMapper::toDomain).toList();
    }
}
