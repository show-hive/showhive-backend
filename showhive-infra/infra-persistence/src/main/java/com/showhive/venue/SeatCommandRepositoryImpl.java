package com.showhive.venue;

import com.showhive.venue.command.SeatCommandJpaRepository;
import com.showhive.venue.domain.Seat;
import com.showhive.venue.entity.SeatEntity;
import com.showhive.venue.mapper.SeatMapper;
import com.showhive.venue.query.VenueQueryJpaRepository;
import com.showhive.venue.repository.command.SeatCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SeatCommandRepositoryImpl implements SeatCommandRepository {
    private final VenueQueryJpaRepository venueQueryJpaRepository;
    private final SeatCommandJpaRepository seatCommandJpaRepository;
    private final SeatMapper seatMapper;

    @Override
    public Seat create(Seat seat) {
        SeatEntity entity = seatMapper.toEntity(seat);
        seatCommandJpaRepository.save(entity);

        return seatMapper.toDomain(entity);
    }
}
