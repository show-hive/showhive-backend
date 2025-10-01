package com.showhive.venue.exception;

import com.showhive.ShowHiveException;
import lombok.Getter;

@Getter
public class SeatException extends ShowHiveException {

    public SeatException(SeatErrorCode seatErrorCode) {
        super(seatErrorCode.getMessage(), seatErrorCode.getStatusCode());
    }
}
