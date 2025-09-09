package com.showhive.member.repository;

import com.showhive.member.domain.Member;

import java.util.Optional;

public interface MemberRepository {
    void signUp(Member member);
    void login(Member member);

    Member save(Member member);

    Optional<Member> findByUsername(String username);

    //  로컬 로그인용: 이메일 + (비번이 null이 아닌 계정)
    Optional<Member> findByEmailAndPasswordIsNotNull(String email);

    // 회원가입 중복 체크용(정책에 따라 이메일 전체 중복 차단)
    Optional<Member> findByEmailIgnoreCase(String email);

    // 로그인 조회용(로컬만)
    Optional<Member> findByEmailIgnoreCaseAndPasswordIsNotNull(String email);

    // 이메일로 기존 사용자 찾고 소셜 계정 attach  - 회원가입 중복 체크
    Optional<Member> findByEmail(String email);


}
