package com.showhive.venue.domain;

import com.showhive.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class VenueDomain extends BaseEntity {
    private VenueId id;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String contactNumber;
    private String link;
    private List<ZoneDomain> zones = new ArrayList<>();

    public void addZone(ZoneDomain zone) {
        zones.add(zone);
    }
}
