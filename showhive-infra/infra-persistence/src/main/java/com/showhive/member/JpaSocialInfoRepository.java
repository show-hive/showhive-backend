package com.showhive.member;

import com.showhive.member.domain.ProviderType;
import com.showhive.member.domain.SocialInfo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSocialInfoRepository extends JpaRepository<SocialInfo, Long> {

    Optional<SocialInfo> findByProviderTypeAndProviderId(ProviderType providerType, String providerId);
}
