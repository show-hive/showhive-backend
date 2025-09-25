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
                          AND (:keyword IS NULL OR sg.grade LIKE %:keyword%)
                ORDER BY sg.id DESC
            """)
    Slice<SeatGrade> findSliceByKeywordAndIdLessThan(@Param("lastGradeId") long lastGradeId, Pageable pageable,
                                                     @Param("keyword") String keyword);

    @Query("""
                SELECT sg FROM SeatGrade sg
                WHERE sg.id > :lastGradeId
                          AND (:keyword IS NULL OR sg.grade LIKE %:keyword%)
                ORDER BY sg.id ASC
            """)
    Slice<SeatGrade> findSliceByKeywordAndIdGreaterThan(@Param("lastGradeId") long lastGradeId, Pageable pageable,
                                                        @Param("keyword") String keyword);
}
