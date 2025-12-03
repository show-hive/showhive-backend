package com.showhive.admin.ticketing_seats;

import com.showhive.performance.domain.PerformanceSessionId;
import com.showhive.performance_seat.domain.SessionSeatDomain;
import com.showhive.performance_seat.domain.SessionSeatId;
import com.showhive.performance_seat.domain.SessionSeatStatus;
import com.showhive.performance_seat.reposotory.command.SessionSeatCommandRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(profiles = {"default"})
@SpringBootTest
public class TicketingSeatTest {
    @Autowired
    SessionSeatCommandRepository sessionSeatCommandRepository;

    @Test
    public void createSeat() {
        SessionSeatDomain domain =
                SessionSeatDomain.builder()
                        .sessionSeatId(new SessionSeatId(1L, 2L))
                        .performanceSessionId(
                                PerformanceSessionId.of(1L, 1L)
                        )
                        .status(SessionSeatStatus.AVAILABLE)
                        .build();
        sessionSeatCommandRepository.save(domain);
    }
}
