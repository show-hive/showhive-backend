package com.showhive.auth.application;

import com.showhive.auth.api.dto.GoogleTokenResponse;
import com.showhive.auth.api.dto.GoogleUserInfo;
import com.showhive.auth.api.dto.LoginResponse;
import com.showhive.auth.application.dto.AuthDto;
import com.showhive.auth.client.GoogleClient;
import com.showhive.member.domain.Member;
import com.showhive.member.domain.ProviderType;
import com.showhive.member.domain.SocialInfo;
import com.showhive.member.repository.MemberRepository;
import com.showhive.member.repository.SocialInfoRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SocialLoginUseCase {

    private final GoogleClient googleClient;
    private final MemberRepository memberRepository;
    private final SocialInfoRepository socialInfoRepository;
    // private final KakaoClient kakaoClient;
    private final TokenManager tokenManager;

    @Transactional
    public LoginResponse googleLogin(AuthDto authDto) {

        GoogleTokenResponse googleTokenResponse = googleClient.requestToken(authDto);
        GoogleUserInfo googleUserInfo = googleClient.requestUserInfo(googleTokenResponse);
        Optional<SocialInfo> socialAccount = socialInfoRepository.findByProviderTypeAndProviderId(ProviderType.GOOGLE,
                googleUserInfo.sub());

        if (socialAccount.isPresent()) {
            return issueTokens(socialAccount.get().getMember());
        }
        Member member = createMember(googleUserInfo);
        createSocialInfo(member, googleUserInfo);
        return issueTokens(member);
    }

    private Member createMember(GoogleUserInfo googleUserInfo) {
        Member member = Member.create(googleUserInfo.email(), googleUserInfo.name());
        memberRepository.save(member);
        return member;
    }

    private void createSocialInfo(Member member, GoogleUserInfo googleUserInfo) {
        SocialInfo socialInfo = SocialInfo.create(member, ProviderType.GOOGLE, googleUserInfo.sub(),
                googleUserInfo.name());
        socialInfoRepository.save(socialInfo);
    }

    private LoginResponse issueTokens(Member member) {
        String accessToken = tokenManager.createAccessToken(member);
        String refreshToken = tokenManager.createRefreshToken(member);
        member.saveRefreshToken(refreshToken);
        return new LoginResponse(accessToken, refreshToken);
    }
}

