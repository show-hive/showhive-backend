package com.showhive.apis.dto;

import com.showhive.application.command.MemberSignUpCommand;

public record MemberSignUpRequest(String name) {
    public MemberSignUpCommand toCommand() {
        return new MemberSignUpCommand(name);
    }
}
