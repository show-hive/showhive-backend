package com.showhive.venue.mapper;

import com.showhive.venue.domain.Seat;
import com.showhive.venue.domain.SeatGrade;
import com.showhive.venue.domain.SeatGradeId;
import com.showhive.venue.domain.SeatId;
import com.showhive.venue.domain.VenueId;
import com.showhive.venue.entity.SeatEntity;
import com.showhive.venue.entity.SeatGradeEntity;
import org.springframework.stereotype.Component;

@Component
public class SeatMapper {
    public SeatEntity toEntity(Seat seat) {
        if (seat == null) {
            return null;
        }

        return SeatEntity.builder()
                .id(seat.getId() != null ? seat.getId().getSeatId() : null)
                .seatColumn(seat.getColumn())
                .seatRow(seat.getRow())
                .seatFloor(seat.getFloor())
                .seatType(seat.getType())
                .seatGrade(toSeatGradeEntity(seat.getGrade()))
                .build();
    }

    public Seat toDomain(SeatEntity entity) {
        if (entity == null) {
            return null;
        }

        return Seat.builder()
                .id(new SeatId(entity.getId()))
                .column(entity.getSeatColumn())
                .row(entity.getSeatRow())
                .floor(entity.getSeatFloor())
                .type(entity.getSeatType())
                .grade(toSeatGradeDomain(entity.getSeatGrade()))
                .venueId(entity.getVenue() != null ? VenueId.of(entity.getVenue().getId()) : null)
                .build();
    }

    private SeatGrade toSeatGradeDomain(SeatGradeEntity entity) {
        if (entity == null) {
            return null;
        }
        return SeatGrade.builder()
                .id(new SeatGradeId(entity.getId()))
                .grade(entity.getGrade())
                .build();
    }

    private SeatGradeEntity toSeatGradeEntity(SeatGrade grade) {
        if (grade == null) {
            return null;
        }
        return SeatGradeEntity.builder()
                .id(grade.getId() != null ? grade.getId().getSeatGradeId() : null)
                .grade(grade.getGrade())
                .build();
    }
}
