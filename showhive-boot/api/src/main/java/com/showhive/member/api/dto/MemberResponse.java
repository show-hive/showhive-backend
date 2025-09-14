package com.showhive.member.api.dto;

import com.showhive.member.domain.Member;

public record MemberResponse(
        long id,
        String username
) {
    public static MemberResponse from(Member member) {
        return new MemberResponse(member.getId(), member.getUsername());
    }
}
