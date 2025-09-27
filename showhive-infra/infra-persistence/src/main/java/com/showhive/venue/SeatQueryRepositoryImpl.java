package com.showhive.venue;

import com.showhive.venue.domain.Seat;
import com.showhive.venue.query.SeatQueryJpaRepository;
import com.showhive.venue.repository.query.SeatQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SeatQueryRepositoryImpl implements SeatQueryRepository {

    private final SeatQueryJpaRepository seatQueryJpaRepository;

    @Override
    public Optional<Seat> findById(long seatId) {
        return seatQueryJpaRepository.findById(seatId);
    }
}
