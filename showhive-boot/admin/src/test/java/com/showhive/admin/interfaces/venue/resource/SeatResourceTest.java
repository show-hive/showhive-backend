package com.showhive.admin.interfaces.venue.resource;

import com.showhive.admin.interfaces.BaseResourceTest;
import com.showhive.admin.interfaces.venue.dto.SeatRequest;
import com.showhive.member.domain.Member;
import com.showhive.venue.domain.SeatGrade;
import com.showhive.venue.domain.SeatType;
import com.showhive.venue.domain.Venue;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

class SeatResourceTest extends BaseResourceTest {

    private String accessToken;

    @BeforeEach
    void auth() {
        Member member = memberGenerator.generateAdmin("admin");
        accessToken = tokenManager.createAccessToken(member);
    }

    @DisplayName("좌석을 생성할 수 있다.")
    @Test
    void create_seat() {
        Venue venue = venueGenerator.generateVenue();
        SeatGrade seatGrade = seatGradeGenerator.generateSeatGrade("A");

        SeatRequest createRequest = new SeatRequest(seatGrade.getId(), null,
                null, null, SeatType.STANDING.name());

        given()
                .contentType(ContentType.JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .when()
                .body(createRequest)
                .post("/admin/v1/venues/" + venue.getId() + "/seats")
                .then().statusCode(HttpStatus.CREATED.value());
    }

    @DisplayName("유효한 좌석 타입이 아니면 예외가 발생한다.")
    @Test
    void throw_exception_when_seat_type_is_invalid() {
        Venue venue = venueGenerator.generateVenue();
        SeatGrade seatGrade = seatGradeGenerator.generateSeatGrade("A");

        String invalidSeatType = "stand";
        SeatRequest createRequest = new SeatRequest(seatGrade.getId(), null,
                null, null, invalidSeatType);

        given()
                .contentType(ContentType.JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .when()
                .body(createRequest)
                .post("/admin/v1/venues/" + venue.getId() + "/seats")
                .then().statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
