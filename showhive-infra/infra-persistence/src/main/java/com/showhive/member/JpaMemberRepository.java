package com.showhive.member;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.showhive.member.domain.Member;

public interface JpaMemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByRefreshToken(String refreshToken);
}
