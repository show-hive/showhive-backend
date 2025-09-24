package com.showhive.member.domain;

import com.showhive.BaseEntity;
import com.showhive.file.domain.File;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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

    private String phone;

    @OneToOne(fetch = FetchType.LAZY)
    private File file;

    @Column(name = "member_role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "member_grade")
    @Enumerated(EnumType.STRING)
    private Grade grade;

    // TODO : Redis에 나중에 저장?
    private String refreshToken;

    public static Member create(String email, String username) {
        return Member.builder()
                .email(email)
                .username(username)
                .grade(Grade.WELCOME)
                .role(Role.USER)
                .build();
    }

    public static Member createAdmin(String email, String username) {
        return Member.builder()
                .email(email)
                .username(username)
                .grade(Grade.WELCOME)
                .role(Role.ADMIN)
                .build();
    }

    // createMember, createAdmin 만들어야됨

    public void saveRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void deleteRefreshToken() {
        this.refreshToken = null;
    }

    public boolean isSameMember(long memberId) {
        return this.id == memberId;
    }

    public boolean canAccess(Role otherRole) {
        return this.role.canAccess(otherRole);
    }
}
