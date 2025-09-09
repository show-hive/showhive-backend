package com.showhive.auth.application;

import com.showhive.auth.application.command.LoginResultCommand;
import com.showhive.auth.application.command.SocialLoginCommand;
import com.showhive.auth.application.command.SocialUpsertCommand;
import com.showhive.auth.oauth.OAuthGateway;
import com.showhive.auth.oauth.SocialProfileDto;
import com.showhive.config.util.JwtTokenProvider;
import com.showhive.member.domain.Member;
import com.showhive.member.domain.SocialInfo;
import com.showhive.member.repository.MemberRepository;
import com.showhive.member.repository.SocialInfoRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class SocialLoginUseCase {

    private final OAuthGateway oAuth;                    // infra (토큰/유저조회)
    private final MemberRepository memberRepository;     // data 포트
    private final SocialInfoRepository socialInfoRepository; // data 포트
    private final JwtTokenProvider jwt;

    public LoginResultCommand login(SocialLoginCommand command) {
        // 1) code -> access token
        String accessToken = oAuth.exchangeCodeForAccessToken(command.provider(), command.code(), command.stateOrNull());

        // 2) token -> profile
        SocialProfileDto profile = oAuth.fetchProfile(command.provider(), accessToken);

        // 3) upsert
        Member member = socialInfoRepository
                .findByProviderAndProviderUserId(profile.provider(), profile.providerId())
                .map(social -> {
                    social.updateProfile(profile.email(), profile.name());
                    Member info = social.getMember();
                    info.mergeProfileFromSocial(profile.name(), profile.email());
                    return info;
                })
                .orElseGet(() -> {
                    Member existing = (profile.email() != null)
                            ? memberRepository.findByEmailIgnoreCase(profile.email()).orElse(null)
                            : null;

                    if (existing == null) {
                        // 신규 회원 생성 (업서트 커맨드 사용해 toDomain)
                        Member newMember = new SocialUpsertCommand(
                                profile.provider(), profile.providerId(), profile.email(), profile.name()
                        ).toDomain();

                        SocialInfo socialInfo = SocialInfo.of(profile.provider(), profile.providerId(), profile.email(), profile.name());
                        newMember.addSocialInfo(socialInfo);

                        return memberRepository.save(newMember);
                    } else {
                        existing.mergeProfileFromSocial(profile.name(), profile.email());
                        SocialInfo s = SocialInfo.of(profile.provider(), profile.providerId(), profile.email(), profile.name());
                        existing.addSocialInfo(s);
                        return memberRepository.save(existing);
                    }
                });

        // 4) 토큰 발급 및 저장
        String newAccessToken = jwt.generateAccessToken(member.getUsername(), member.getRole());
        String refreshToken = jwt.generateRefreshToken(member.getUsername());
        member.makeRefreshToken(refreshToken);
        memberRepository.save(member);

        return new LoginResultCommand(member.getId(), member.getUsername(), newAccessToken, refreshToken);
    }
}