package com.showhive.venue.entity;

import com.showhive.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "venues")
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Venue extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venue_id")
    private Long id;

    private String name;

    private String address;

    private Double latitude;

    private Double longitude;

    private String contactNumber;

    private String link;

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
}
