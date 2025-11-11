package com.showhive.admin.interfaces.venue.resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.showhive.admin.interfaces.BaseResourceTest;
import com.showhive.admin.interfaces.venue.dto.SeatGradeListResponse;
import com.showhive.admin.interfaces.venue.dto.SeatGradeRequest;
import com.showhive.admin.interfaces.venue.dto.SeatGradeResponse;
import com.showhive.member.domain.Member;
import com.showhive.venue.entity.SeatGradeEntity;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

class SeatGradeResourceTest extends BaseResourceTest {

    private String accessToken;

    @BeforeEach
    void auth() {
        Member member = memberGenerator.generateAdmin("admin");
        accessToken = tokenManager.createAccessToken(member);
    }

    @DisplayName("좌석 등급을 생성할 수 있다.")
    @Test
    void create_seat_grade() {
        SeatGradeRequest createRequest = new SeatGradeRequest("R");

        given()
                .contentType(ContentType.JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .when()
                .body(createRequest)
                .post("/admin/v1/seat-grade")
                .then().statusCode(HttpStatus.CREATED.value());
    }

    @DisplayName("좌석 등급 목록을 조회할 수 있다.")
    @Test
    void read_all_seat_grades() {
        seatGradeGenerator.generateSeatGrade("R");
        seatGradeGenerator.generateSeatGrade("S");
        seatGradeGenerator.generateSeatGrade("A");

        SeatGradeListResponse seatGradeListResponse = given()
                .contentType(ContentType.JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .when()
                .get("/admin/v1/seat-grade")
                .then().statusCode(HttpStatus.OK.value())
                .extract().as(SeatGradeListResponse.class);

        assertAll(
                () -> assertThat(seatGradeListResponse.seatGrades()).hasSize(3),
                () -> assertThat(seatGradeListResponse.loadable()).isFalse()
        );
    }

    @DisplayName("특정 좌석 등급을 조회할 수 있다.")
    @Test
    void read_seat_grade() {
        SeatGradeEntity seatGrade = seatGradeGenerator.generateSeatGrade("R");

        SeatGradeResponse seatGradeResponse = given()
                .contentType(ContentType.JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .when()
                .get("/admin/v1/seat-grade/{seatGradeId}", seatGrade.getId())
                .then().statusCode(HttpStatus.OK.value())
                .extract().as(SeatGradeResponse.class);

        assertThat(seatGradeResponse.grade()).isEqualTo(seatGrade.getGrade());
    }

    @DisplayName("특정 좌석 등급을 수정할 수 있다.")
    @Test
    void edit_seat_grade() {
        SeatGradeEntity seatGrade = seatGradeGenerator.generateSeatGrade("R");
        SeatGradeRequest updateRequest = new SeatGradeRequest("S");

        given()
                .contentType(ContentType.JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .queryParam("seatGradeId", seatGrade.getId())
                .when()
                .body(updateRequest)
                .put("/admin/v1/seat-grade/{seatGradeId}", seatGrade.getId())
                .then().statusCode(HttpStatus.OK.value());
    }

    @DisplayName("특정 좌석 등급을 삭제할 수 있다.")
    @Test
    void delete_seat_grade() {
        SeatGradeEntity seatGrade = seatGradeGenerator.generateSeatGrade("R");

        given()
                .contentType(ContentType.JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .queryParam("seatGradeId", seatGrade.getId())
                .when()
                .delete("/admin/v1/seat-grade/{seatGradeId}", seatGrade.getId())
                .then().statusCode(HttpStatus.NO_CONTENT.value());
    }

    @DisplayName("존재하지 않는 특정 좌석 등급을 삭제할 시 예외가 발생한다.")
    @Test
    void cannot_delete_when_seat_grade_not_exists() {
        SeatGradeEntity seatGrade = seatGradeGenerator.generateSeatGrade("R");

        given()
                .contentType(ContentType.JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .queryParam("seatGradeId", seatGrade.getId())
                .when()
                .delete("/admin/v1/seat-grade/{seatGradeId}", seatGrade.getId())
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());

        given()
                .contentType(ContentType.JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .queryParam("seatGradeId", seatGrade.getId())
                .when()
                .delete("/admin/v1/seat-grade/{seatGradeId}", seatGrade.getId())
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
