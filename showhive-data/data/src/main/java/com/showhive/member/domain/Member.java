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

    public static Member create(String email, String username) {
        return Member.builder()
                .email(email)
                .username(username)
                .build();
    }

    public void saveRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void deleteRefreshToken() {
        this.refreshToken = null;
    }
}
