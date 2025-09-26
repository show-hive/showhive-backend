package com.showhive.admin.fixture;

import com.showhive.member.domain.Member;
import com.showhive.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberGenerator {

    @Autowired
    private MemberRepository memberRepository;

    public Member generateAdmin(String username) {
        Member admin = Member.createAdmin(username + "@admin.com", username);
        memberRepository.save(admin);
        return admin;
    }
}
