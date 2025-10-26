package com.showhive.venue;

import com.showhive.venue.command.SeatCommandJpaRepository;
import com.showhive.venue.entity.Seat;
import com.showhive.venue.repository.command.SeatCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SeatCommandRepositoryImpl implements SeatCommandRepository {

    private final SeatCommandJpaRepository seatCommandJpaRepository;

    @Override
    public Seat create(Seat seat) {
        return seatCommandJpaRepository.save(seat);
    }
}
