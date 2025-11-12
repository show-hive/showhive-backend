package com.showhive.admin.application.command.usecase.venue.seat.impl;

import com.showhive.admin.application.command.dto.venue.SeatDto;
import com.showhive.admin.application.command.usecase.venue.seat.CreateSeatUseCase;
import com.showhive.admin.interfaces.venue.dto.SeatResponse;
import com.showhive.venue.domain.Seat;
import com.showhive.venue.domain.SeatGrade;
import com.showhive.venue.domain.SeatGradeId;
import com.showhive.venue.domain.Venue;
import com.showhive.venue.domain.VenueId;
import com.showhive.venue.entity.SeatType;
import com.showhive.venue.repository.command.SeatCommandRepository;
import com.showhive.venue.repository.query.SeatGradeQueryRepository;
import com.showhive.venue.repository.query.VenueQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateSeatUseCaseImpl implements CreateSeatUseCase {

    private final SeatCommandRepository seatCommandRepository;
    private final SeatGradeQueryRepository seatGradeQueryRepository;
    private final VenueQueryRepository venueQueryRepository;

    @Override
    public SeatResponse handle(SeatDto seatDto, long venueId) {
        SeatGrade seatGrade = getSeatGrade(seatDto);
        Venue venue = getVenue(venueId);
        SeatType seatType = SeatType.findSeatType(seatDto.seatType());

        Seat seat = Seat.create(
                VenueId.of(venue.getId().getVenueId()),
                seatDto.seatColumn(), seatDto.seatRow(), seatDto.seatFloor(),
                seatType, seatGrade
        );
        Seat savedSeat = seatCommandRepository.create(seat);

        return new SeatResponse(savedSeat, venue);
    }

    private Venue getVenue(long venueId) {
        return venueQueryRepository.findById(venueId);
    }

    private SeatGrade getSeatGrade(SeatDto seatDto) {
        return seatGradeQueryRepository.findById(seatDto.seatGradeId())
                .map(seatGradeEntity -> SeatGrade.builder()
                        .id(new SeatGradeId(seatGradeEntity.getId()))
                        .grade(seatGradeEntity.getGrade())
                        .build()
                ).orElse(null);
    }
}
