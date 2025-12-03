package com.showhive.venue.command;

import com.showhive.venue.entity.VenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueCommandJpaRepository extends JpaRepository<VenueEntity, Long> {
}
