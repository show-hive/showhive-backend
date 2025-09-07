package com.showhive.member.repository;

import com.showhive.member.domain.Member;

import java.util.Optional;

public interface MemberRepository {
    void signUp(Member member);

    Optional<Member> findById(long memberId);
}
