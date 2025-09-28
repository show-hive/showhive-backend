package com.showhive.admin.interfaces.venue.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record VenueRequest(

        @NotBlank
        @Schema(description = "공연장 이름", example = "예술의전당 오페라극장", requiredMode = Schema.RequiredMode.REQUIRED)
        String name,

        @Schema(description = "공연장 주소", example = "서울특별시 서초구 남부순환로 2406", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String address,

        @Schema(description = "위도", example = "37.4783", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Double latitude,

        @Schema(description = "경도", example = "127.0112", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Double longitude,

        @Schema(description = "연락처", example = "02-580-1300", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String contactNumber,

        @NotBlank
        @Schema(description = "공연장 링크", example = "https://www.sac.or.kr", requiredMode = Schema.RequiredMode.REQUIRED)
        String link
) {
}
