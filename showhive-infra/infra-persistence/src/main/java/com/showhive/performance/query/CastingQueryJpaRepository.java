package com.showhive.performance.query;

import com.showhive.performance.entity.CastingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CastingQueryJpaRepository extends JpaRepository<CastingEntity, Long> {
}
