package com.showhive.venue.query;

import com.showhive.venue.domain.SeatGrade;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SeatGradeQueryJpaRepository extends JpaRepository<SeatGrade, Long> {

    @Query("""
                SELECT sg FROM SeatGrade sg
                WHERE sg.id < :lastGradeId
                ORDER BY sg.id DESC
            """)
    Slice<SeatGrade> findAllByLessThanId(@Param("lastGradeId") long lastGradeId, Pageable pageable);
}
