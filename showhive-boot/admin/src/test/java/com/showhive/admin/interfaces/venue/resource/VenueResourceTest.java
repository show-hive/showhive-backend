package com.showhive.admin.interfaces.venue.resource;

import com.showhive.admin.interfaces.BaseResourceTest;
import com.showhive.admin.interfaces.venue.dto.VenueRequest;
import com.showhive.admin.interfaces.venue.dto.VenueResponse;
import com.showhive.member.domain.Member;
import com.showhive.venue.domain.Venue;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class VenueResourceTest extends BaseResourceTest {

    private String accessToken;

    @BeforeEach
    void auth() {
        Member member = memberGenerator.generateAdmin("admin");
        accessToken = tokenManager.createAccessToken(member);
    }

    @DisplayName("공연장을 생성할 수 있다.")
    @Test
    void create_seat() {
        String name = "세종문화회관 대극장";
        String link = "https://www.sejongpac.or.kr";
        VenueRequest createRequest = new VenueRequest(name, null, null, null, null, link);

        given()
                .contentType(ContentType.JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .when()
                .body(createRequest)
                .post("/admin/v1/venues")
                .then().statusCode(HttpStatus.CREATED.value());
    }

    @DisplayName("이름이 중복되는 공연장은 생성할 수 없다.")
    @Test
    void throw_exception_when_venue_name_already_exists() {
        String name = "세종문화회관 대극장";
        venueGenerator.generateVenue(name);

        String link = "https://www.sejongpac.or.kr";
        VenueRequest createRequest = new VenueRequest(name, null, null, null, null, link);

        given()
                .contentType(ContentType.JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .when()
                .body(createRequest)
                .post("/admin/v1/venues")
                .then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("특정 공연장을 조회할 수 있다.")
    @Test
    void read_seat() {
        Venue venue = venueGenerator.generateVenue("임지현 아트 센터");

        VenueResponse venueResponse = given()
                .contentType(ContentType.JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .when()
                .get("/admin/v1/venues/{venueId}", venue.getId())
                .then().statusCode(HttpStatus.OK.value())
                .extract().as(VenueResponse.class);

        assertAll(
                () -> assertThat(venueResponse.venueId()).isEqualTo(venue.getId()),
                () -> assertThat(venueResponse.name()).isEqualTo(venue.getName())
        );
    }
}
