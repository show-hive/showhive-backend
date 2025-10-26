package com.showhive.venue;

import com.showhive.venue.entity.Seat;
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

    @Override
    public Optional<Seat> findById(long seatId) {
        return seatQueryJpaRepository.findById(seatId);
    }

    @Override
    public List<Seat> findAllByVenueIdWithSeatGrade(Long venueId) {
        return seatQueryJpaRepository.findByVenue_Id(venueId);
    }
}
