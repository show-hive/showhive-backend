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
    public Member save(Member member) {jpaMemberRepository.save(member); return member;}

    @Override
    public void signUp(Member member) {
        jpaMemberRepository.save(member);
    }

    @Override
    public void login(Member member) {
        jpaMemberRepository.save(member);
    }

    @Override
    public Optional<Member> findByUsername(String username) {
        return jpaMemberRepository.findByUsername(username);
    }

    @Override
    public Optional<Member> findByEmailAndPasswordIsNotNull(String email) {
        return jpaMemberRepository.findByEmailAndPasswordIsNotNull(email);
    }

    @Override
    public Optional<Member> findByEmailIgnoreCase(String email) {
        return jpaMemberRepository.findByEmailIgnoreCase(email);
    }

    @Override
    public Optional<Member> findByEmailIgnoreCaseAndPasswordIsNotNull(String email) {
        return jpaMemberRepository.findByEmailIgnoreCaseAndPasswordIsNotNull(email);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return jpaMemberRepository.findByEmail(email);
    }
}
// 지금 MemberRepositoryImpl은 도메인 레이어(data)의 MemberRepository 포트를 구현하는 클래스
// 내부적으로 Spring Data JPA 리포지토리(JpaMemberRepository)를 사용