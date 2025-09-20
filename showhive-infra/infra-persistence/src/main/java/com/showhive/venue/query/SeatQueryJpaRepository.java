package com.showhive.venue.query;

import com.showhive.venue.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatQueryJpaRepository extends JpaRepository<Seat, Long> {

}
