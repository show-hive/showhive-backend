package com.showhive.performance.command;

import com.showhive.performance.entity.Casting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CastingCommandJpaRepository extends JpaRepository<Casting, Long> {
}
