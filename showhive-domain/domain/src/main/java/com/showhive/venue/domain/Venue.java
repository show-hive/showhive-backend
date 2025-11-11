package com.showhive.venue.domain;

import java.time.LocalDateTime;
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
public class Venue {
    private VenueId id;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String contactNumber;
    private String link;
    private List<Zone> zones = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Venue create(String name, String address, Double latitude,
                               Double longitude, String contactNumber, String link) {
        return Venue.builder()
                .name(name)
                .address(address)
                .latitude(latitude)
                .longitude(longitude)
                .contactNumber(contactNumber)
                .link(link)
                .build();
    }

    public void addZone(Zone zone) {
        zones.add(zone);
    }

    public void updateInfo(String name, String address, Double latitude, Double longitude, String contactNumber,
                           String link) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.contactNumber = contactNumber;
        this.link = link;
    }
}
