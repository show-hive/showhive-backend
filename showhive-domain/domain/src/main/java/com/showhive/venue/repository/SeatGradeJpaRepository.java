package com.showhive.venue.repository;

import com.showhive.venue.domain.SeatGrade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatGradeJpaRepository extends JpaRepository<SeatGrade, Long> {
}
