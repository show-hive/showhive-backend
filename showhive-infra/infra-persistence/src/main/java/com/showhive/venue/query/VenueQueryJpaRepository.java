package com.showhive.venue.query;

import com.showhive.venue.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueQueryJpaRepository extends JpaRepository<Venue, Long> {

    boolean existsByName(String name);
}
