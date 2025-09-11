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

@Table(name = "social_infos")
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    // TODO : code로 바꿔야됨
    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    private String providerId;

    private String username;

    public static SocialInfo create(Member member, ProviderType providerType, String providerId, String username) {

        return SocialInfo.builder()
                .member(member)
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
