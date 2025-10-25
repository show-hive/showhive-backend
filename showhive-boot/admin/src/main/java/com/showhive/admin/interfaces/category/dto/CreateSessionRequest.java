package com.showhive.admin.interfaces.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "공연 회차 생성 요청 DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSessionRequest {

    @NotNull(message = "공연 회차 정보 목록은 필수입니다.")
    @Size(min = 1, message = "최소 한 개 이상의 공연 회차 정보를 입력해야 합니다.")
    @Valid
    @Schema(description = "공연 회차 일자별 목록", requiredMode = RequiredMode.REQUIRED)
    private List<@Valid SessionRequest> sessions;

    @Schema(description = "공연 일자별 회차 정보")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SessionRequest {

        @NotNull(message = "공연 일자는 필수입니다.")
        @FutureOrPresent(message = "공연 일자는 오늘 이후여야 합니다.")
        @Schema(description = "공연 일자", example = "2025-12-24", requiredMode = RequiredMode.REQUIRED)
        private LocalDate scheduledAt;

        @NotNull(message = "해당 일자의 회차 목록은 필수입니다.")
        @Size(min = 1, message = "최소 한 개 이상의 회차를 입력해야 합니다.")
        @Valid
        @Schema(description = "해당 일자에 속한 회차 목록", requiredMode = RequiredMode.REQUIRED)
        private List<@Valid SessionDto> sessions;

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        @Schema(description = "공연 회차 세부 정보")
        public static class SessionDto {

            @NotBlank(message = "회차 이름은 필수입니다.")
            @Size(max = 50, message = "회차 이름은 50자 이하로 입력해야 합니다.")
            @Schema(description = "회차 이름", example = "1회차", requiredMode = RequiredMode.REQUIRED)
            private String sessionName;

            @NotNull(message = "회차 시간은 필수입니다.")
            @Schema(description = "회차 시작 시간", example = "19:30", requiredMode = RequiredMode.REQUIRED)
            private LocalTime sessionTime;
        }
    }
}
