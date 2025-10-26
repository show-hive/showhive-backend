package com.showhive.venue.command;

import com.showhive.venue.entity.SeatGrade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatGradeCommandJpaRepository extends JpaRepository<SeatGrade, Long> {
}
