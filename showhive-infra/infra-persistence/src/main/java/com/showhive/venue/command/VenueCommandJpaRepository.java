package com.showhive.venue.command;

import com.showhive.venue.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueCommandJpaRepository extends JpaRepository<Venue, Long> {
}
