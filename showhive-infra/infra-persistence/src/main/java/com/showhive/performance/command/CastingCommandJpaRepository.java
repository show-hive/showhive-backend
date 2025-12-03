package com.showhive.performance.command;

import com.showhive.performance.entity.CastingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CastingCommandJpaRepository extends JpaRepository<CastingEntity, Long> {
}
