package com.showhive.admin.application.command.usecase;

import com.showhive.admin.DataBaseCleaner;
import com.showhive.admin.fixture.CategoryGenerator;
import com.showhive.admin.fixture.MemberGenerator;
import com.showhive.admin.fixture.PerformanceCategoryGenerator;
import com.showhive.admin.fixture.PerformanceGenerator;
import com.showhive.admin.fixture.SeatGenerator;
import com.showhive.admin.fixture.VenueGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@ExtendWith(DataBaseCleaner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public abstract class BaseUseCaseTest {

    @Autowired
    protected MemberGenerator memberGenerator;

    @Autowired
    protected CategoryGenerator categoryGenerator;

    @Autowired
    protected PerformanceGenerator performanceGenerator;

    @Autowired
    protected PerformanceCategoryGenerator performanceCategoryGenerator;

    @Autowired
    protected SeatGenerator seatGenerator;

    @Autowired
    protected VenueGenerator venueGenerator;
}
