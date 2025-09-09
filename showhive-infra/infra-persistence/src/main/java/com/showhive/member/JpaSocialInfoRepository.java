package com.showhive.member;

import com.showhive.member.domain.SocialInfo;
import com.showhive.member.domain.SocialInfo.Provider;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSocialInfoRepository extends JpaRepository<SocialInfo, Long> {

    Optional<SocialInfo> findByProviderAndProviderUserId(Provider provider, String providerUserId);

}
