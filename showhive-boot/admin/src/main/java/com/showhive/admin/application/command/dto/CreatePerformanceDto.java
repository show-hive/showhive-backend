package com.showhive.admin.application.command.dto;

import com.showhive.admin.interfaces.performance.dto.CreatePerformanceRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record CreatePerformanceDto(

        @NotBlank(message = "공연 제목은 필수 입력 항목입니다.")
        @Size(max = 100, message = "공연 제목은 100자 이하로 입력해야 합니다.")
        @Schema(description = "공연 제목", example = "뮤지컬 레 미제라블", requiredMode = RequiredMode.REQUIRED)
        String title,

        @NotNull(message = "공연장 ID는 필수입니다.")
        @Positive(message = "공연장 ID는 1 이상의 값이어야 합니다.")
        @Schema(description = "공연장 ID", example = "12", requiredMode = RequiredMode.REQUIRED)
        Long venueId,

        @NotNull(message = "공연 시간은 필수입니다.")
        @Schema(description = "공연 러닝타임 (예: 2시간 30분 → PT2H30M)", example = "PT2H30M", requiredMode = RequiredMode.REQUIRED)
        Duration runningTime,

        @Min(value = 0, message = "연령 제한은 0 이상이어야 합니다.")
        @Schema(description = "관람 가능 최소 연령 (null 시 전체 관람가)", example = "15", requiredMode = RequiredMode.NOT_REQUIRED)
        Short ageRating,

        @Size(max = 500, message = "혜택 정보는 500자 이하로 입력해야 합니다.")
        @Schema(description = "혜택 정보", example = "조기예매 시 10% 할인", requiredMode = RequiredMode.NOT_REQUIRED)
        String advantage,

        @NotBlank(message = "공연 정보는 필수 입력 항목입니다.")
        @Schema(description = "공연 상세 정보 (HTML 형식)", example = "<p>세계적인 명작 뮤지컬을 새롭게 만나다!</p>", requiredMode = RequiredMode.REQUIRED)
        String performanceInfo,

        @NotNull(message = "공연 카테고리 ID 목록은 필수입니다.")
        @Size(min = 1, message = "최소 한 개 이상의 카테고리를 선택해야 합니다.")
        @Schema(description = "공연 카테고리 ID 목록", example = "[1, 3, 5]", requiredMode = RequiredMode.REQUIRED)
        List<@Positive(message = "카테고리 ID는 1 이상의 값이어야 합니다.") Long> categoryIds,

        @NotNull(message = "예약 시작일시는 필수입니다.")
        @Future(message = "예약 시작일시는 현재 시각 이후여야 합니다.")
        @Schema(description = "예약 시작 일시", example = "2025-11-01T10:00:00", requiredMode = RequiredMode.REQUIRED)
        LocalDateTime bookStartedAt,

        @NotNull(message = "예약 종료일시는 필수입니다.")
        @Future(message = "예약 종료일시는 현재 시각 이후여야 합니다.")
        @Schema(description = "예약 종료 일시", example = "2025-12-31T23:59:59", requiredMode = RequiredMode.REQUIRED)
        LocalDateTime bookEndedAt

) {
    public static CreatePerformanceDto of(CreatePerformanceRequest request) {
        return CreatePerformanceDto.builder()
                .title(request.title())
                .venueId(request.venueId())
                .runningTime(request.runningTime())
                .ageRating(request.ageRating())
                .advantage(request.advantage())
                .performanceInfo(request.performanceInfo())
                .categoryIds(request.categoryIds())
                .bookStartedAt(request.bookStartedAt())
                .bookEndedAt(request.bookEndedAt())
                .build();
    }
}
