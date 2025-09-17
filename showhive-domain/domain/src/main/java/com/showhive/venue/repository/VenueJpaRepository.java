package com.showhive.venue.repository;

import com.showhive.venue.domain.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueJpaRepository extends JpaRepository<Venue, Long> {
}
