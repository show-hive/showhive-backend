package com.showhive.member.repository;

import com.showhive.member.domain.ProviderType;
import com.showhive.member.domain.SocialInfo;
import java.util.Optional;

public interface SocialInfoRepository {

    Optional<SocialInfo> findByProviderTypeAndProviderId(ProviderType providerType, String providerId);

    void save(SocialInfo socialInfo);
}
