package com.showhive.venue.entity;

import com.showhive.venue.exception.SeatGradeErrorCode;
import com.showhive.venue.exception.SeatGradeException;
import lombok.Getter;

@Getter
public enum SeatGradeSortCondition {

    ID("id"),
    NAME("name");

    private final String field;

    SeatGradeSortCondition(String field) {
        this.field = field;
    }

    public static SeatGradeSortCondition from(String sortCondition) {
        try {
            return SeatGradeSortCondition.valueOf(sortCondition.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new SeatGradeException(SeatGradeErrorCode.SEAT_GRADE_SORT_CONDITION_NOT_VALID);
        }
    }
}
