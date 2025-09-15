package com.showhive.auth.application.query;

import com.showhive.auth.api.dto.GoogleUserInfo;
import com.showhive.auth.api.dto.KakaoUserInfo;
import com.showhive.auth.api.dto.LoginResponse;
import com.showhive.auth.api.dto.NaverUserInfo;
import com.showhive.auth.api.dto.TokenResponse;

import com.showhive.auth.utils.TokenManager;
import com.showhive.auth.application.dto.AuthDto;
import com.showhive.auth.client.GoogleClient;
import com.showhive.auth.client.KakaoClient;
import com.showhive.auth.client.NaverClient;
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
    private final KakaoClient kakaoClient;
    private final NaverClient naverClient;
    private final MemberRepository memberRepository;
    private final SocialInfoRepository socialInfoRepository;
    private final TokenManager tokenManager;

    @Transactional
    public LoginResponse googleLogin(AuthDto authDto) {
        TokenResponse tokenResponse = googleClient.requestToken(authDto);
        GoogleUserInfo googleUserInfo = googleClient.requestUserInfo(tokenResponse);
        Optional<SocialInfo> socialAccount = socialInfoRepository.findByProviderTypeAndProviderId(ProviderType.GOOGLE,
                googleUserInfo.sub());

        if (socialAccount.isPresent()) {
            return issueTokens(socialAccount.get().getMember());
        }
        Member member = createGoogleMember(googleUserInfo);
        createSocialGoogleInfo(member, googleUserInfo);
        return issueTokens(member);
    }

    @Transactional
    public LoginResponse kakaoLogin(AuthDto authDto) {
        TokenResponse tokenResponse = kakaoClient.requestToken(authDto);
        KakaoUserInfo kakaoUserInfo = kakaoClient.requestUserInfo(tokenResponse);
        Optional<SocialInfo> socialAccount = socialInfoRepository.findByProviderTypeAndProviderId(ProviderType.KAKAO,
                kakaoUserInfo.id());

        if (socialAccount.isPresent()) {
            return issueTokens(socialAccount.get().getMember());
        }
        Member member = createKakaoMember(kakaoUserInfo);
        createSocialKakaoInfo(member, kakaoUserInfo);
        return issueTokens(member);
    }

    @Transactional
    public LoginResponse naverLogin(AuthDto authDto) {
        TokenResponse tokenResponse = naverClient.requestToken(authDto);
        NaverUserInfo.Response profile = naverClient.requestUserInfo(tokenResponse);
        Optional<SocialInfo> socialAccount =
                socialInfoRepository.findByProviderTypeAndProviderId(ProviderType.NAVER, profile.id());

        if (socialAccount.isPresent()) {
            return issueTokens(socialAccount.get().getMember());
        }
//        Member member = createNaverMember(naverUserInfo);
//        createSocialNaverInfo(member, naverUserInfo);

        Member member = createNaverMember(profile);
        createSocialNaverInfo(member, profile);
        return issueTokens(member);
    }

    private Member createGoogleMember(GoogleUserInfo googleUserInfo) {
        Member member = Member.create(googleUserInfo.email(), googleUserInfo.name());
        memberRepository.save(member);
        return member;
    }

    private Member createKakaoMember(KakaoUserInfo kakaoUserInfo) {
        Member member = Member.create(kakaoUserInfo.email(), kakaoUserInfo.nickname());
        memberRepository.save(member);
        return member;
    }

    private Member createNaverMember(NaverUserInfo.Response naverUserInfo) {
        Member member = Member.create(naverUserInfo.email(), naverUserInfo.name());
        memberRepository.save(member);
        return member;
    }

    private void createSocialGoogleInfo(Member member, GoogleUserInfo googleUserInfo) {
        SocialInfo socialInfo = SocialInfo.create(member, ProviderType.GOOGLE, googleUserInfo.sub(),
                googleUserInfo.name());
        socialInfoRepository.save(socialInfo);
    }

    private void createSocialKakaoInfo(Member member, KakaoUserInfo kakaoUserInfo) {
        SocialInfo socialInfo = SocialInfo.create(member, ProviderType.KAKAO, kakaoUserInfo.id(),
                kakaoUserInfo.nickname());
        socialInfoRepository.save(socialInfo);
    }

    private void createSocialNaverInfo(Member member, NaverUserInfo.Response naverUserInfo) {
        SocialInfo socialInfo = SocialInfo.create(member, ProviderType.NAVER, naverUserInfo.id(),
                naverUserInfo.name());
        socialInfoRepository.save(socialInfo);
    }

    private LoginResponse issueTokens(Member member) {
        String accessToken = tokenManager.createAccessToken(member);
        String refreshToken = tokenManager.createRefreshToken(member);
        member.saveRefreshToken(refreshToken);
        return new LoginResponse(accessToken, refreshToken);
    }
}

