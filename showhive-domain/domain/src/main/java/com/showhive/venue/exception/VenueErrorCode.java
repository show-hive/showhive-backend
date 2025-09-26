package com.showhive.venue.exception;

import lombok.Getter;

@Getter
public enum VenueErrorCode {

    //4XX
    VENUE_NOT_FOUND(404, "존재하지 않는 공연장입니다."),
    ;

    private final int statusCode;
    private final String message;

    VenueErrorCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
