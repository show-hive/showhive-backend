package com.showhive.admin.interfaces.category.resource;

import com.showhive.admin.interfaces.category.dto.CreateCategoryRequest;
import com.showhive.admin.interfaces.category.dto.DetailCategoryResponse;
import com.showhive.admin.interfaces.category.dto.UpdateCategoryRequest;
import com.showhive.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "카테고리 API")
public interface CategoryFacade {
    @PostMapping
    @Operation(summary = "카테고리 생성", description = "카테고리를 추가함. 카테고리는 공연에 연관지음")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "카테고리 추가 완료.", content = {
                    @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "카테고리 추가에 필요한 값 검증 실패.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "카테고리를 찾을 수 없습니다.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    void create(@Valid @RequestBody CreateCategoryRequest categoryRequest);

    @Operation(summary = "카테고리 삭제", description = "카테고리를 삭제함. 비활성화 처리")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "카테고리 삭제 완료.", content = {
                    @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "카테고리를 찾을 수 없습니다.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    void delete(Long id);

    @Operation(summary = "카테고리 단건 조회", description = "카테고리 ID로 카테고리 단건을 조회함. 조회시 하위의 카테고리를 포함함.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "카테고리 조회 완료.", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "카테고리 조회에 필요한 카테고리 값 검증 실패.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "카테고리를 찾을 수 없습니다.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/{id}")
    DetailCategoryResponse detail(Long id);

    @Operation(summary = "카테고리 수정", description = "카테고리를 수정함.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "카테고리 수정 완료.", content = {
                    @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "카테고리 수정에 필요한 값 검증 실패.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "카테고리를 찾을 수 없습니다.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    void update(Long id, UpdateCategoryRequest categoryRequest);

}
