package com.showhive.member.api.dto;

import com.showhive.member.application.command.MemberSignUpCommand;

public record MemberSignUpRequest(String name) {
    public MemberSignUpCommand toCommand() {
        return new MemberSignUpCommand(name);
    }
}
