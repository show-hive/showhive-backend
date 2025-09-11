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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "social_infos", uniqueConstraints = {
        @UniqueConstraint(name = "uk_provider_pid", columnNames = {"provider", "provider_user_id"})
},
        indexes = {
                @Index(name = "idx_social_user", columnList = "user_id")})
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SocialInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "social_info_id")
    private Long id;

    // TODO : code로 바꿔야됨
    @Enumerated(EnumType.STRING)
    private ProviderType providerType;                  // GOOGLE/KAKAO/NAVER

    private String providerId;                  // 소셜의 고유 ID (예: kakao id)

    // private String email;                       // 소셜이 준 이메일(동의 없으면 null)

    private String username;                    // 소셜 닉네임/이름

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    public static SocialInfo of(ProviderType providerType, String providerId, String username) {

        return SocialInfo.builder()
                .providerType(providerType)
                .providerId(providerId)
                .username(username)
                .build();
    }

    //소셜 프로필 갱신 (이메일/닉네임이 비어있거나 다르면 갱신)
//    public void updateProfile(String email, String nickname) {
//        if (email != null && (this.email == null || !this.email.equalsIgnoreCase(email))) {
//            this.email = email;
//        }
//        if (nickname != null && (this.nickname == null || !this.nickname.equals(nickname))) {
//            this.nickname = nickname;
//        }
//    }
//
//    //소유자 연결
//    public void attachTo(Member owner) {
//        this.member = owner;
//    }
//
//    // 소유자 해제
//    public void detach() {
//        this.member = null;
//    }

}
