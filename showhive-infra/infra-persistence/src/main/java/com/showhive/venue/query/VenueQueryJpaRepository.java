package com.showhive.venue.query;

import com.showhive.venue.entity.VenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueQueryJpaRepository extends JpaRepository<VenueEntity, Long> {

    boolean existsByName(String name);
}
