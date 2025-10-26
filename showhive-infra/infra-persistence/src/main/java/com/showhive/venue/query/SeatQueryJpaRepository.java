package com.showhive.venue.query;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import com.showhive.venue.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatQueryJpaRepository extends JpaRepository<Seat, Long> {

    @EntityGraph(attributePaths = {"seatGrade"})
    List<Seat> findByVenue_Id(Long venueId);
}
