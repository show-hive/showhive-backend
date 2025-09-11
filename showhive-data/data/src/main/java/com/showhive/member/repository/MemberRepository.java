package com.showhive.member.repository;

import com.showhive.member.domain.Member;

import java.util.Optional;

public interface MemberRepository {


    Optional<Member> findById(long memberId);


    void save(Member member);
}
