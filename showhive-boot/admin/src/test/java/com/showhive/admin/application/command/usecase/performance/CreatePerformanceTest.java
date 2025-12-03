package com.showhive.admin.application.command.usecase.performance;

import com.showhive.admin.fixture.SeatGenerator;
import com.showhive.admin.fixture.SeatGradeGenerator;
import com.showhive.admin.fixture.VenueGenerator;
import com.showhive.venue.domain.Seat;
import com.showhive.venue.domain.SeatGrade;
import com.showhive.venue.domain.SeatGradeId;
import com.showhive.venue.domain.Venue;
import com.showhive.venue.entity.SeatGradeEntity;
import com.showhive.venue.mapper.VenueMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CreatePerformanceTest {
    @Autowired
    private VenueGenerator venueGenerator;

    @Autowired
    private SeatGenerator seatGenerator;

    @Autowired
    private SeatGradeGenerator seatGradeGenerator;

    @Autowired
    private VenueMapper venueMapper;

    @Test
    void createPerformanceTest() {
        Venue venue = venueGenerator.generateVenue("GS아트센터");
        SeatGradeEntity seatGradeEntity = seatGradeGenerator.generateSeatGrade("B");

        SeatGrade seatGrade = SeatGrade.builder()
                .id(new SeatGradeId(seatGradeEntity.getId()))
                .grade(seatGradeEntity.getGrade())
                .build();
        Seat seat = seatGenerator.generateSeat(venue, seatGrade);
    }
}
