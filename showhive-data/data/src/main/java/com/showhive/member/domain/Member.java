package com.showhive.member.domain;

import com.showhive.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "members")
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;

    private String email;

    // TODO : file 조인 걸어야됨

    // TODO: Code 연결
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "code_id")
    // private Code roleCode;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "code_id")
    // private Code gradeCode;

    // TODO : Redis에 나중에 저장?
    private String refreshToken;

    // 소셜 계정 목록 (1:N)
//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
//    @Builder.Default
//    private List<SocialInfo> socialInfos = new ArrayList<>();

    // 소셜 프로필 정보로 사용자 프로필을 병합 (이메일/이름이 비어있거나 다르면 갱신)
//    public void mergeProfileFromSocial(String name, String email) {
//        if (email != null && (this.email == null || !this.email.equalsIgnoreCase(email))) {
//            this.email = email;
//        }
//        if (name != null && (this.name == null || !this.name.equals(name))) {
//            this.name = name;
//        }
//    }

//    public void addSocialInfo(SocialInfo social_Info) {
//        if (social_Info == null) {
//            return;
//        }
//        social_Info.attachTo(this); // 소유자 연결
//        if (!this.socialInfos.contains(social_Info)) {
//            this.socialInfos.add(social_Info);
//        }
//    }

    public static Member create(String email, String username) {
        return Member.builder()
                .email(email)
                .username(username)
                .build();
    }
//
//    public static Member signUpSocial(Provider provider, String providerUserId, String email, String name) {
//        if (provider == null) {
//            throw new IllegalArgumentException("provider 불가");
//        }
//        if (providerUserId == null || providerUserId.isBlank()) {
//            throw new IllegalArgumentException("providerUserId 불가");
//        }
//
//        String username = provider.name().toLowerCase() + "_" + providerUserId;
//        return Member.builder()
//                .email(email)
//                .username(username)
//                .build();
//    }

    // 리프레시 토큰 발급/갱신
//    public void makeRefreshToken(String newToken) {
//        if (newToken == null || newToken.isBlank()) {
//            throw new IllegalArgumentException("유효하지 않은 리프레시 토큰");
//        }
//
//        this.refreshToken = newToken;
//    }
//
//    // 토큰 폐기 (로그아웃/강제만료)
//    public void deleteRefreshToken() {
//        this.refreshToken = null;
//    }
}
