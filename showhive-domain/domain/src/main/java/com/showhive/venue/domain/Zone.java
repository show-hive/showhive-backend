package com.showhive.venue.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 */
@Getter
@EqualsAndHashCode
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Zone {
    private final List<Seat> seats = new ArrayList<>();
    private ZoneId zoneId;
    private String name;

    public void addSeat(Seat seat) {
        seats.add(seat);
    }
}
