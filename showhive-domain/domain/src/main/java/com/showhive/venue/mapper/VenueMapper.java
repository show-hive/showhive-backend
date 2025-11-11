package com.showhive.venue.mapper;

import com.showhive.venue.domain.Venue;
import com.showhive.venue.domain.VenueId;
import com.showhive.venue.domain.Zone;
import com.showhive.venue.entity.VenueEntity;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class VenueMapper {

    public Venue toDomain(VenueEntity entity) {
        if (entity == null) {
            return null;
        }

        return Venue.builder()
                .id(VenueId.of(entity.getId()))
                .name(entity.getName())
                .address(entity.getAddress())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .contactNumber(entity.getContactNumber())
                .link(entity.getLink())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .zones(Collections.emptyList()) // zone은 별도로 주입
                .build();
    }

    public VenueEntity toEntity(Venue domain) {
        if (domain == null) {
            return null;
        }

        return VenueEntity.builder()
                .id(Optional.ofNullable(domain.getId())
                        .map(VenueId::getVenueId)
                        .orElse(null)
                )
                .name(domain.getName())
                .address(domain.getAddress())
                .latitude(domain.getLatitude())
                .longitude(domain.getLongitude())
                .contactNumber(domain.getContactNumber())
                .link(domain.getLink())
                .build();
    }

    public Venue toDomain(VenueEntity entity, List<Zone> zones) {
        Venue domain = toDomain(entity);
        if (domain != null && zones != null) {
            zones.forEach(domain::addZone);
        }
        return domain;
    }
}
