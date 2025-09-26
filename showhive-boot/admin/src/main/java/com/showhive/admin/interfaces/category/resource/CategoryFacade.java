package com.showhive.admin.interfaces.category.resource;

import com.showhive.admin.interfaces.category.dto.CreateCategoryRequest;
import com.showhive.admin.interfaces.category.dto.UpdateCategoryRequest;
import com.showhive.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "카테고리 API")
public interface CategoryFacade {
    @Operation(summary = "카테고리 생성", description = "카테고리를 추가함. 카테고리는 공연에 연관지음")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "카테고리 추가 완료.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "카테고리 추가에 필요한 값 검증 실패.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "카테고리를 찾을 수 없습니다.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    void create(CreateCategoryRequest categoryRequest);

    @Operation(summary = "카테고리 수정", description = "카테고리를 수정함.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "카테고리 수정 완료.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "카테고리 수정에 필요한 값 검증 실패.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "카테고리를 찾을 수 없습니다.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    void update(Long id, UpdateCategoryRequest categoryRequest);
}
