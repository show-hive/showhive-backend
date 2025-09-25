package com.showhive.admin.interfaces.venue.resource;

import com.showhive.admin.interfaces.venue.dto.SeatGradeListResponse;
import com.showhive.admin.interfaces.venue.dto.SeatGradeRequest;
import com.showhive.admin.interfaces.venue.dto.SeatGradeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Seat Grade API")
@SecurityRequirement(name = "Authorization")
public interface SeatGradeResourceFacade {

    @Operation(
            summary = "좌석 등급 생성",
            requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = SeatGradeRequest.class))),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "좌석 등급 생성 성공")
            }
    )
    ResponseEntity<Void> createSeatGrade(SeatGradeRequest seatGradeRequest);

    @Operation(
            summary = "모든 좌석 등급 조회",
            description = "마지막 조회한 좌석 등급 ID 기준으로 다음 좌석 등급 목록을 조회합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "모든 좌석 등급 조회 성공",
                            content = @Content(schema = @Schema(implementation = SeatGradeListResponse.class)))
            }
    )
    ResponseEntity<SeatGradeListResponse> readAllSeatGrades(
            @Parameter(name = "size", description = "페이지 크기", example = "20") int pageSize,
            @Parameter(name = "lastGradeId", description = "마지막 조회한 좌석 등급 ID", example = "0") long lastGradeId,
            @Parameter(name = "keyword", description = "검색 키워드", example = "a") String keyword,
            @Parameter(name = "direction", description = "정렬 방향", example = "desc") String direction
    );

    @Operation(
            summary = "특정 좌석 등급 조회",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "좌석 등급 조회 성공",
                            content = @Content(schema = @Schema(implementation = SeatGradeResponse.class)))
            }
    )
    ResponseEntity<SeatGradeResponse> readSeatGrade(
            @Parameter(name = "seatGradeId", description = "조회할 좌석 등급 ID", example = "1") long seatGradeId
    );

    @Operation(
            summary = "좌석 등급 수정",
            requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = SeatGradeRequest.class))),
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "좌석 등급 수정 성공")
            }
    )
    ResponseEntity<Void> updatePerformance(
            @Parameter(name = "seatGradeId", description = "수정할 좌석 등급 ID", example = "1") long seatGradeId,
            SeatGradeRequest seatGradeRequest
    );

    @Operation(
            summary = "좌석 등급 삭제",
            responses = {
                    @ApiResponse(responseCode = "204",
                            description = "좌석 등급 삭제 성공")
            }
    )
    ResponseEntity<Void> deleteSeatGrade(
            @Parameter(name = "seatGradeId", description = "삭제할 좌석 등급 ID", example = "1") long seatGradeId
    );
}
