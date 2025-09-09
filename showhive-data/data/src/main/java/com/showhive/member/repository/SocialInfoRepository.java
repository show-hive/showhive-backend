package com.showhive.member.repository;


import com.showhive.member.domain.SocialInfo;
import com.showhive.member.domain.SocialInfo.Provider;
import java.util.Optional;

public interface SocialInfoRepository {

    Optional<SocialInfo> findByProviderAndProviderUserId(
            Provider provider, String providerUserId);


}
