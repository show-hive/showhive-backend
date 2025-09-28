package com.showhive.admin.interfaces;

import com.showhive.admin.DataBaseCleaner;
import com.showhive.admin.fixture.MemberGenerator;
import com.showhive.admin.fixture.SeatGenerator;
import com.showhive.admin.fixture.SeatGradeGenerator;
import com.showhive.admin.fixture.VenueGenerator;
import com.showhive.auth.TokenManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@ExtendWith(DataBaseCleaner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseResourceTest {

    @Autowired
    protected TokenManager tokenManager;

    @Autowired
    protected MemberGenerator memberGenerator;

    @Autowired
    protected SeatGradeGenerator seatGradeGenerator;

    @Autowired
    protected SeatGenerator seatGenerator;

    @Autowired
    protected VenueGenerator venueGenerator;

    @LocalServerPort
    private int port;

    private RequestSpecification spec;

    @BeforeEach
    void setEnvironment() {
        RestAssured.port = port;
        spec = new RequestSpecBuilder()
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    protected RequestSpecification given() {
        return RestAssured.given(spec);
    }
}
