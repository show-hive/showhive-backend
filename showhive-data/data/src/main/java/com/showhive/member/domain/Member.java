package com.showhive.member.domain;

import com.showhive.BaseEntity;
import com.showhive.code.domain.Code;
import com.showhive.file.domain.File;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    // TODO : String으로 받아올지 or File 조인걸지
    // @OneToOne(fetch = FetchType.LAZY)
    // private File file;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_code_id")
    private Code roleCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grade_code_id")
    private Code gradeCode;

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

    public boolean isSameMember(long memberId) {
        return this.id == memberId;
    }
}
