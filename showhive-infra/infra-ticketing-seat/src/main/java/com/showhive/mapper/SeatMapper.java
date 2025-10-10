package com.showhive.mapper;

import com.showhive.document.SeatDocument;
import com.showhive.document.SeatDocument.SeatGradeDocument;
import com.showhive.venue.entity.Seat;

public class SeatMapper {
    public SeatDocument toDocument(Seat seat) {
        return SeatDocument.builder()
                .seatId(seat.getId())
                .row(seat.getSeatRow())
                .column(seat.getSeatColumn())
                .floor(seat.getSeatFloor())
                .seatGrade(toSeatGradeDocument(seat.getSeatGrade()))
                .type(seat.getSeatType().name())
                .build();
    }

    public Seat toDomain(SeatDocument document) {
        return Seat.builder()
                .id(document.getSeatId())
                .seatRow(document.getRow())
                .seatColumn(document.getColumn())
                .seatFloor(document.getFloor())
                .seatGrade(toSeatGrade(document.getSeatGrade()))
                .seatType(SeatType.findSeatType(document.getType()))
                .build();
    }

    private SeatGradeDocument toSeatGradeDocument(SeatGrade seatGrade) {
        if (seatGrade == null) {
            return null;
        }
        return SeatGradeDocument.builder()
                .id(seatGrade.getId())
                .grade(seatGrade.getGrade())
                .build();
    }

    private SeatGrade toSeatGrade(SeatGradeDocument seatGradeDocument) {
        if (seatGradeDocument == null) {
            return null;
        }
        return SeatGrade.builder()
                .id(seatGradeDocument.getId())
                .grade(seatGradeDocument.getGrade())
                .build();
    }
}
