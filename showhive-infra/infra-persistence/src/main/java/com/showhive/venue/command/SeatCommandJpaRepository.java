package com.showhive.venue.command;

import com.showhive.venue.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatCommandJpaRepository extends JpaRepository<Seat, Long> {

}
