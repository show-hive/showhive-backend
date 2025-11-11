package com.showhive.admin.fixture;

import com.showhive.venue.entity.SeatEntity;
import com.showhive.venue.entity.SeatGradeEntity;
import com.showhive.venue.entity.SeatType;
import com.showhive.venue.entity.VenueEntity;
import com.showhive.venue.repository.command.SeatCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SeatGenerator {

    @Autowired
    private SeatCommandRepository seatCommandRepository;
    
    public SeatEntity generateSeat(VenueEntity venue, SeatGradeEntity seatGrade) {
        Short seatRow = 1;
        Short seatFloor = 1;
        SeatEntity seat = SeatEntity.create(venue, "A", seatRow, seatFloor, SeatType.RESERVED, seatGrade);
        return seatCommandRepository.create(seat);
    }
}
