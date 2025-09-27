package com.showhive.venue.exception;

import com.showhive.ShowHiveException;
import lombok.Getter;

@Getter
public class VenueException extends ShowHiveException {

    public VenueException(VenueErrorCode venueErrorCode) {
        super(venueErrorCode.getMessage(), venueErrorCode.getStatusCode());
    }
}
