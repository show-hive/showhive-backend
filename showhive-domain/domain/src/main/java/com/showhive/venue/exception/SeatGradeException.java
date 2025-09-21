package com.showhive.venue.exception;

import com.showhive.ShowHiveException;
import lombok.Getter;

@Getter
public class SeatGradeException extends ShowHiveException {

    public SeatGradeException(SeatGradeErrorCode seatGradeErrorCode) {
        super(seatGradeErrorCode.getMessage(), seatGradeErrorCode.getStatusCode());
    }
}
