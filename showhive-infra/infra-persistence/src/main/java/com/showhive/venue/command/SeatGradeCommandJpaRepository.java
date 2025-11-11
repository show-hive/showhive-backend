package com.showhive.venue.command;

import com.showhive.venue.entity.SeatGradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatGradeCommandJpaRepository extends JpaRepository<SeatGradeEntity, Long> {
}
