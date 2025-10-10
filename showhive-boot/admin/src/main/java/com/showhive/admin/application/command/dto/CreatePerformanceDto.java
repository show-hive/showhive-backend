package com.showhive.admin.application.command.dto;

import com.showhive.admin.interfaces.performance.dto.CreatePerformanceRequest;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
public record CreatePerformanceDto(
        String title,
        Long venueId,
        Duration runningTime,
        Short ageRating,
        String advantage,
        String performanceInfo,
        List<Long> categoryIds,
        List<ScheduleDto> scheduleDtoList,
        LocalDateTime bookStartedAt,
        LocalDateTime bookEndedAt
) {
    public static CreatePerformanceDto of(CreatePerformanceRequest request) {
        return CreatePerformanceDto.builder()
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

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ScheduleDto {
        private LocalDate scheduledAt;
        private List<SessionDto> sessions;

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class SessionDto {
            String scheduleName;
            String scheduledTime;
        }
    }
}
