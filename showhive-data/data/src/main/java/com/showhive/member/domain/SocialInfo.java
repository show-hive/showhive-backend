package com.showhive.member.domain;

import com.showhive.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "social_infos", uniqueConstraints = {
        @UniqueConstraint(name = "uk_provider_pid", columnNames = {"provider", "provider_user_id"})
},
        indexes = {
                @Index(name = "idx_social_user", columnList = "user_id")})
public class SocialInfo extends BaseEntity {

    public enum Provider {GOOGLE, KAKAO, NAVER}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long social_id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Provider provider;                  // GOOGLE/KAKAO/NAVER

    @Column(name = "provider_user_id", nullable = false, length = 100)
    private String providerUserId;              // 소셜의 고유 ID (예: kakao id)

    @Column(length = 255)
    private String email;                       // 소셜이 준 이메일(동의 없으면 null)

    @Column(length = 100)
    private String nickname;                    // 소셜 닉네임/이름

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    public static SocialInfo of(Provider provider, String providerUserId, String email,
                                String nickname) {
        if (provider == null) {
            throw new IllegalArgumentException("provider 불가");
        }

        if (providerUserId == null || providerUserId.isBlank()) {
            throw new IllegalArgumentException("providerUserId 불가");
        }

        return SocialInfo.builder()
                .provider(provider)
                .providerUserId(providerUserId)
                .email(email)
                .nickname(nickname)
                .build();
    }

    //소셜 프로필 갱신 (이메일/닉네임이 비어있거나 다르면 갱신)
    public void updateProfile(String email, String nickname) {
        if (email != null && (this.email == null || !this.email.equalsIgnoreCase(email))) {
            this.email = email;
        }
        if (nickname != null && (this.nickname == null || !this.nickname.equals(nickname))) {
            this.nickname = nickname;
        }
    }

    //소유자 연결
    public void attachTo(Member owner) {
        this.member = owner;
    }

    // 소유자 해제
    public void detach() {
        this.member = null;
    }

}
