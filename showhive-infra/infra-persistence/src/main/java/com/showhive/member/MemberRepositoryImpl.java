package com.showhive.member;

import com.showhive.exception.member.MemberErrorCode;
import com.showhive.exception.member.MemberException;
import com.showhive.member.domain.Member;
import com.showhive.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final JpaMemberRepository jpaMemberRepository;

    @Override
    // TODO: 사용 안 할 시 삭제 예정
    public void signUp(Member member) {
        jpaMemberRepository.save(member);
    }

    @Override
    // TODO: 사용 안 할 시 삭제 예정
    public Member findById(long memberId) {
        return jpaMemberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
    }
}
