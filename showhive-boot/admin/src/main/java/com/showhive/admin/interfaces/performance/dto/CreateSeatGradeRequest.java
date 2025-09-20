package com.showhive.admin.interfaces.performance.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CreateSeatGradeRequest(
        @NotNull @NotBlank @Length(min = 1) String name
) {
}
