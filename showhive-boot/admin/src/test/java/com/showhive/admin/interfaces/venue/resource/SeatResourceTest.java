package com.showhive.admin.interfaces.venue.resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.showhive.admin.interfaces.BaseResourceTest;
import com.showhive.admin.interfaces.venue.dto.SeatRequest;
import com.showhive.admin.interfaces.venue.dto.SeatResponse;
import com.showhive.member.domain.Member;
import com.showhive.venue.entity.SeatEntity;
import com.showhive.venue.entity.SeatGradeEntity;
import com.showhive.venue.entity.SeatType;
import com.showhive.venue.entity.VenueEntity;
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
        VenueEntity venue = venueGenerator.generateVenue("임지현 아트 센터");
        SeatGradeEntity seatGrade = seatGradeGenerator.generateSeatGrade("A");

        SeatRequest createRequest = new SeatRequest(seatGrade.getId(), null,
                null, null, SeatType.STANDING.name());

        given()
                .contentType(ContentType.JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .when()
                .body(createRequest)
                .post("/admin/v1/venues/{venueId}/seats", venue.getId())
                .then().statusCode(HttpStatus.CREATED.value());
    }

    @DisplayName("유효한 좌석 타입이 아니면 예외가 발생한다.")
    @Test
    void throw_exception_when_seat_type_is_invalid() {
        VenueEntity venue = venueGenerator.generateVenue("임지현 아트 센터");
        SeatGradeEntity seatGrade = seatGradeGenerator.generateSeatGrade("A");

        String invalidSeatType = "stand";
        SeatRequest createRequest = new SeatRequest(seatGrade.getId(), null,
                null, null, invalidSeatType);

        given()
                .contentType(ContentType.JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .when()
                .body(createRequest)
                .post("/admin/v1/venues/{venueId}/seats", venue.getId())
                .then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("특정 좌석을 조회할 수 있다.")
    @Test
    void read_seat() {
        VenueEntity venue = venueGenerator.generateVenue("임지현 아트 센터");
        SeatGradeEntity seatGrade = seatGradeGenerator.generateSeatGrade("A");
        SeatEntity seat = seatGenerator.generateSeat(venue, seatGrade);

        SeatResponse seatResponse = given()
                .contentType(ContentType.JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .when()
                .get("/admin/v1/venues/{venueId}/seats/{seatId}", venue.getId(), seat.getId())
                .then().statusCode(HttpStatus.OK.value())
                .extract().as(SeatResponse.class);

        assertAll(
                () -> assertThat(seatResponse.seatId()).isEqualTo(seat.getId()),
                () -> assertThat(seatResponse.venueResponse().venueId()).isEqualTo(venue.getId())
        );
    }
}
