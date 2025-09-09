package com.showhive.member.domain;

import com.showhive.BaseEntity;
import com.showhive.member.domain.SocialInfo.Provider;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "members",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_member_username", columnNames = "username"),
                @UniqueConstraint(name = "uk_member_email", columnNames = "email")
        }
)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //앱 내부 식별용 아이디(표시명 아님). 소셜이면 kakao_123 처럼 부여
    @Column(nullable = false, length = 100)
    private String username;

    // 로컬 로그인만 사용. 소셜 사용자는 null 가능
    @Column(length = 100)
    private String password;

    // 사용자 실명 또는 닉네임(정책에 맞게 사용)
    @Column(length = 100)
    private String name;

    @Column(length = 255)
    private String email;

    @Column(nullable = false, length = 20)
    private String role; // 예: ROLE_USER, ROLE_ADMIN

    @Column(length = 500)
    private String refreshToken;

    // 소셜 계정 목록 (1:N)
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<SocialInfo> socialInfos = new ArrayList<>();

    // 소셜 프로필 정보로 사용자 프로필을 병합 (이메일/이름이 비어있거나 다르면 갱신)
    public void mergeProfileFromSocial(String name, String email) {
        if (email != null && (this.email == null || !this.email.equalsIgnoreCase(email))) {
            this.email = email;
        }
        if (name != null && (this.name == null || !this.name.equals(name))) {
            this.name = name;
        }
    }

    public void addSocialInfo(SocialInfo social_Info) {
        if (social_Info == null) {
            return;
        }
        social_Info.attachTo(this); // 소유자 연결
        if (!this.socialInfos.contains(social_Info)) {
            this.socialInfos.add(social_Info);
        }
    }

    public static Member signUp(String email, String password, String username, String name, String encodedPassword) {
        return Member.builder()
                .email(email)
                .username(username)  // username을 이메일 기반으로 지정
                .password(encodedPassword)
                .name(name)
                .role("ROLE_USER")
                .build();
    }

    public static Member signUpSocial(Provider provider, String providerUserId, String email, String name) {
        if (provider == null) {
            throw new IllegalArgumentException("provider 불가");
        }
        if (providerUserId == null || providerUserId.isBlank()) {
            throw new IllegalArgumentException("providerUserId 불가");
        }

        String username = provider.name().toLowerCase() + "_" + providerUserId;
        return Member.builder()
                .email(email)
                .username(username)
                .password(null)
                .role("ROLE_USER")
                .build();
    }

    // 리프레시 토큰 발급/갱신
    public void makeRefreshToken(String newToken) {
        if (newToken == null || newToken.isBlank()) {
            throw new IllegalArgumentException("유효하지 않은 리프레시 토큰");
        }

        this.refreshToken = newToken;
    }

    // 토큰 폐기 (로그아웃/강제만료)
    public void deleteRefreshToken() {
        this.refreshToken = null;
    }
}
