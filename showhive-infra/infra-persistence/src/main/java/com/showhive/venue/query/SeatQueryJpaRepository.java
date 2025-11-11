package com.showhive.venue.query;

import com.showhive.venue.entity.SeatEntity;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatQueryJpaRepository extends JpaRepository<SeatEntity, Long> {

    @EntityGraph(attributePaths = {"seatGrade"})
    List<SeatEntity> findByVenue_Id(Long venueId);
}
