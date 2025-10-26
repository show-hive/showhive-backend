package com.showhive.venue.query;

import com.showhive.venue.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatQueryJpaRepository extends JpaRepository<Seat, Long> {

}
