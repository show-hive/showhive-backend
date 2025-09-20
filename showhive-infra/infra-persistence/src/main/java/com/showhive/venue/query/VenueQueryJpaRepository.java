package com.showhive.venue.query;

import com.showhive.venue.domain.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueQueryJpaRepository extends JpaRepository<Venue, Long> {
}
