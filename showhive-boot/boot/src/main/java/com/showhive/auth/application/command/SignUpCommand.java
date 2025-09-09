package com.showhive.auth.application.command;

import com.showhive.member.domain.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignUpCommand(
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        String email,

        String username,

        @NotBlank(message = "비밀번호는 필수입니다.")
        String password,
        String name,
        String role
) {
    public Member toDomain(String encodedPassword) {
        String username = email; // 정책: username = email
        return Member.signUp(email, password, username, name, encodedPassword);
    }
}
