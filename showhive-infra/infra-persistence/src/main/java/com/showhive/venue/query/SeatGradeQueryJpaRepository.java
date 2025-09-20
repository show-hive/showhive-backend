package com.showhive.venue.query;

import com.showhive.venue.domain.SeatGrade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatGradeQueryJpaRepository extends JpaRepository<SeatGrade, Long> {
}
