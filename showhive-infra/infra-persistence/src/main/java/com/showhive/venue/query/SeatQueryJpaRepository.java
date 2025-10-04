package com.showhive.venue.query;

import com.showhive.venue.domain.Seat;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatQueryJpaRepository extends JpaRepository<Seat, Long> {

    @EntityGraph(attributePaths = {"seatGrade"})
    List<Seat> findByVenue_Id(Long venueId);
}
