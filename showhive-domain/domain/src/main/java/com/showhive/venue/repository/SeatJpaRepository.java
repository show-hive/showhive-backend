package com.showhive.venue.repository;

import com.showhive.venue.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatJpaRepository extends JpaRepository<Seat, Long> {
}
