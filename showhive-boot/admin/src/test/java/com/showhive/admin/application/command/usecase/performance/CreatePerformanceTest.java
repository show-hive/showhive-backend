package com.showhive.admin.application.command.usecase.performance;

import com.showhive.admin.fixture.SeatGenerator;
import com.showhive.admin.fixture.SeatGradeGenerator;
import com.showhive.admin.fixture.VenueGenerator;
import com.showhive.venue.entity.SeatEntity;
import com.showhive.venue.entity.SeatGradeEntity;
import com.showhive.venue.entity.VenueEntity;
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
        VenueEntity venue = venueGenerator.generateVenue("GS아트센터");
        SeatGradeEntity seatGrade = seatGradeGenerator.generateSeatGrade("B");

        SeatEntity seat = seatGenerator.generateSeat(venue, seatGrade);
    }
}
