package com.showhive.auth.api.dto;

import com.showhive.auth.application.command.SignUpCommand;
import com.showhive.member.application.command.MemberSignUpCommand;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


// 자체 회원가입
public record SignUpRequest(
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        String email,

        String username,

        @NotBlank(message = "비밀번호는 필수입니다.")
        String password,
        String name,
        String role
) {
        public SignUpCommand toCommand() {
                return new SignUpCommand(email, password, username, name,role);
        }
}
