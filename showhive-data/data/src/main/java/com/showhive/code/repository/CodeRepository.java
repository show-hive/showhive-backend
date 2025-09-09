package com.showhive.code.repository;

import com.showhive.code.domain.Code;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeRepository extends JpaRepository<Code, Long> {
}
