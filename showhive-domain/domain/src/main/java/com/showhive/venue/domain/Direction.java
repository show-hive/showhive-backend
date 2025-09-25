package com.showhive.venue.domain;

import com.showhive.venue.exception.SeatGradeErrorCode;
import com.showhive.venue.exception.SeatGradeException;
import lombok.Getter;

@Getter
public enum Direction {
    ASC,
    DESC;

    public static Direction from(String direction) {
        try {
            return Direction.valueOf(direction.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new SeatGradeException(SeatGradeErrorCode.SEAT_GRADE_DIRECTION_NOT_VALID);
        }
    }

}
