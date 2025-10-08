package com.showhive.admin.fixture;

import com.showhive.venue.entity.Seat;
import com.showhive.venue.entity.SeatGrade;
import com.showhive.venue.entity.SeatType;
import com.showhive.venue.entity.Venue;
import com.showhive.venue.repository.command.SeatCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SeatGenerator {

    @Autowired
    private SeatCommandRepository seatCommandRepository;

    public Seat generateSeat(Venue venue, SeatGrade seatGrade) {
        Short seatRow = 1;
        Short seatFloor = 1;
        Seat seat = Seat.create(venue, "A", seatRow, seatFloor, SeatType.RESERVED, seatGrade);
        return seatCommandRepository.create(seat);
    }
}
