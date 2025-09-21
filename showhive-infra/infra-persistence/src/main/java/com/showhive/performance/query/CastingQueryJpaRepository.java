package com.showhive.performance.query;

import com.showhive.performance.domain.Casting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CastingQueryJpaRepository extends JpaRepository<Casting, Long> {
}
