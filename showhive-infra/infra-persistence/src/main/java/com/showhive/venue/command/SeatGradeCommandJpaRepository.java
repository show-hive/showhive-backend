package com.showhive.venue.command;

import com.showhive.venue.domain.SeatGrade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatGradeCommandJpaRepository extends JpaRepository<SeatGrade, Long> {
}
