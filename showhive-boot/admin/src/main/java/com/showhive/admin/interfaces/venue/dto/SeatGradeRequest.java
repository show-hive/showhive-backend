package com.showhive.admin.interfaces.venue.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record SeatGradeRequest(
        @NotBlank
        @Length(min = 1)
        String name
) {
}
