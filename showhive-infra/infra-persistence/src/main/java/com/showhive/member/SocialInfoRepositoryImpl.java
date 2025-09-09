package com.showhive.member;

import com.showhive.member.domain.SocialInfo;
import com.showhive.member.domain.SocialInfo.Provider;
import com.showhive.member.repository.SocialInfoRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SocialInfoRepositoryImpl implements SocialInfoRepository {

    private final JpaSocialInfoRepository jpaSocialInfoRepository;

    @Override
    public Optional<SocialInfo> findByProviderAndProviderUserId(
            Provider provider, String providerUserId) {

        return jpaSocialInfoRepository.findByProviderAndProviderUserId(provider, providerUserId);
    }
}
