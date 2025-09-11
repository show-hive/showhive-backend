package com.showhive.member.api.dto;

import com.showhive.member.application.command.MemberSignUpCommand;
import jakarta.validation.constraints.NotBlank;

public record MemberSignUpRequest(
        @NotBlank
        String name
) {
    public MemberSignUpCommand toCommand() {
        return new MemberSignUpCommand(name);
    }
}
