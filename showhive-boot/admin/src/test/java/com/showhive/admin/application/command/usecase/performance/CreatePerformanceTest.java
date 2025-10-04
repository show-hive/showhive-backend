package com.showhive.admin.application.command.usecase.performance;

import com.showhive.admin.fixture.SeatGenerator;
import com.showhive.admin.fixture.SeatGradeGenerator;
import com.showhive.admin.fixture.VenueGenerator;
import com.showhive.venue.domain.Seat;
import com.showhive.venue.domain.SeatGrade;
import com.showhive.venue.domain.Venue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CreatePerformanceTest {
    @Autowired
    VenueGenerator venueGenerator;

    @Autowired
    SeatGenerator seatGenerator;

    @Autowired
    SeatGradeGenerator seatGradeGenerator;

    @Test
    void createPerformanceTest() {
        Venue venue = venueGenerator.generateVenue("GS아트센터");
        SeatGrade seatGrade = seatGradeGenerator.generateSeatGrade("B");

        Seat seat = seatGenerator.generateSeat(venue, seatGrade);
    }
}
