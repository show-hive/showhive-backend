package com.showhive.member;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.showhive.member.domain.Member;

public interface JpaMemberRepository extends JpaRepository<Member, Long> {


    Optional<Member> findByUsername(String username);

    Optional<Member> findByEmailAndPasswordIsNotNull(String email);

    Optional<Member> findByEmailIgnoreCase(String email);

    Optional<Member> findByEmailIgnoreCaseAndPasswordIsNotNull(String email);

    Optional<Member> findByEmail(String email);
}
