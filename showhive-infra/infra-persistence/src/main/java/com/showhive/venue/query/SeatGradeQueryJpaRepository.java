package com.showhive.venue.query;

import com.showhive.venue.entity.SeatGradeEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SeatGradeQueryJpaRepository extends JpaRepository<SeatGradeEntity, Long> {

    @Query("""
                SELECT sg FROM SeatGradeEntity sg
                WHERE sg.id < :lastGradeId
                          AND (:keyword IS NULL OR sg.grade LIKE %:keyword%)
                ORDER BY sg.id DESC
            """)
    Slice<SeatGradeEntity> findSliceByKeywordAndIdLessThan(@Param("lastGradeId") long lastGradeId, Pageable pageable,
                                                           @Param("keyword") String keyword);

    @Query("""
                SELECT sg FROM SeatGradeEntity sg
                WHERE sg.id > :lastGradeId
                          AND (:keyword IS NULL OR sg.grade LIKE %:keyword%)
                ORDER BY sg.id ASC
            """)
    Slice<SeatGradeEntity> findSliceByKeywordAndIdGreaterThan(@Param("lastGradeId") long lastGradeId, Pageable pageable,
                                                              @Param("keyword") String keyword);
}
