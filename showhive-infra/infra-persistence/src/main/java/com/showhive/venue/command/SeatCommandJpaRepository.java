package com.showhive.venue.command;

import com.showhive.venue.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatCommandJpaRepository extends JpaRepository<SeatEntity, Long> {

}
