package com.showhive.member;

import com.showhive.member.domain.Member;
import com.showhive.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final JpaMemberRepository jpaMemberRepository;

    @Override
    public void save(Member member) {
        jpaMemberRepository.save(member);
    }

    @Override
    public Optional<Member> findById(long memberId) {
        return jpaMemberRepository.findById(memberId);
    }

    @Override
    public Optional<Member> findByRefreshToken(String refreshToken) {
        return jpaMemberRepository.findByRefreshToken(refreshToken);
    }
}
