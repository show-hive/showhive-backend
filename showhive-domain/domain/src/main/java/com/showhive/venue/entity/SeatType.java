package com.showhive.venue.entity;

import com.showhive.venue.exception.SeatErrorCode;
import com.showhive.venue.exception.SeatException;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum SeatType {

    STANDING("스탠딩"),
    RESERVED("지정석"),
    ;

    private final String description;

    SeatType(String description) {
        this.description = description;
    }

    public static SeatType findSeatType(String seatType) {
        return Arrays.stream(SeatType.values())
                .filter(type -> type.name().equalsIgnoreCase(seatType))
                .findFirst()
                .orElseThrow(() -> new SeatException(SeatErrorCode.SEAT_TYPE_NOT_VALID));
    }
}
