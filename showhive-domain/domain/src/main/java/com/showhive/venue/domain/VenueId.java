package com.showhive.venue.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class VenueId {
    private Long venueId;

    public static VenueId of(Long venueId) {
        return VenueId.builder()
                .venueId(venueId)
                .build();
    }
}
