package com.showhive.member;

import com.showhive.member.domain.ProviderType;
import com.showhive.member.domain.SocialInfo;
import com.showhive.member.repository.SocialInfoRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SocialInfoRepositoryImpl implements SocialInfoRepository {

    private final JpaSocialInfoRepository jpaSocialInfoRepository;

    @Override
    public Optional<SocialInfo> findByProviderTypeAndProviderId(ProviderType providerType, String providerId) {
        return jpaSocialInfoRepository.findByProviderTypeAndProviderId(providerType, providerId);
    }

    @Override
    public void save(SocialInfo socialInfo) {
        jpaSocialInfoRepository.save(socialInfo);
    }
}
