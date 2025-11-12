package com.showhive.admin.application.command.usecase.venue.seat.impl;

import com.showhive.admin.application.command.usecase.venue.seat.ReadSeatUseCase;
import com.showhive.admin.interfaces.venue.dto.SeatResponse;
import com.showhive.venue.domain.Seat;
import com.showhive.venue.domain.Venue;
import com.showhive.venue.exception.SeatErrorCode;
import com.showhive.venue.exception.SeatException;
import com.showhive.venue.mapper.SeatMapper;
import com.showhive.venue.repository.query.SeatQueryRepository;
import com.showhive.venue.repository.query.VenueQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadSeatUseCaseImpl implements ReadSeatUseCase {

    private final SeatQueryRepository seatQueryRepository;
    private final VenueQueryRepository venueQueryRepository;
    private final SeatMapper seatMapper;

    @Override
    public SeatResponse handle(long venueId, long seatId) {
        //question: 공연장이랑 좌석 조회를 한번에 하는게 나을지?
        Venue venue = getVenue(venueId);
        Seat seat = seatQueryRepository.findById(seatId)
                .filter(s -> s.isInVenue(venue))
                .orElseThrow(() -> new SeatException(SeatErrorCode.SEAT_NOT_FOUND));
        return new SeatResponse(seat, venue);
    }

    private Venue getVenue(long venueId) {
        return venueQueryRepository.findById(venueId);
    }
}
