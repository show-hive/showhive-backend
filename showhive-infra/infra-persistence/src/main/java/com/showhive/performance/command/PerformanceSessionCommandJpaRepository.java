package com.showhive.performance.command;

import com.showhive.performance.entity.PerformanceSession;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PerformanceSessionCommandJpaRepository extends JpaRepository<PerformanceSession, Long> {
    @Query(
            nativeQuery = true,
            value = """
                    SELECT EXISTS (
                        SELECT 1
                        FROM performance_sessions ps
                        WHERE ps.performance_id = :performanceId
                          AND ps.start_at <= :endAt
                          AND ps.end_at >= :startAt
                    )
                    """
    )
    boolean existsPerformanceSession(Long id, LocalDateTime startAt, LocalDateTime endAt);
}
