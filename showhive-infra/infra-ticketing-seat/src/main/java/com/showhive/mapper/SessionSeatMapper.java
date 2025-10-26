package com.showhive.mapper;

import com.showhive.document.SessionSeatDocument;
import com.showhive.document.SessionSeatDocument.SessionSeatStatusDocument;
import com.showhive.performance.domain.PerformanceSessionId;
import com.showhive.performance_seat.domain.SessionSeatDomain;
import com.showhive.performance_seat.domain.SessionSeatId;
import com.showhive.performance_seat.domain.SessionSeatStatus;
import org.springframework.stereotype.Component;

@Component
public class SessionSeatMapper {
    public SessionSeatDocument toEntity(SessionSeatDomain domain) {
        return SessionSeatDocument.builder()
                .id(SessionSeatIdConverter.toCompositeKey(domain.getSessionSeatId()))
                .seatId(domain.getSessionSeatId().seatId())
                .performanceId(domain.getSessionSeatId().performanceId())
                .performanceSessionId(domain.getPerformanceSessionId().getPerformanceSessionId())
                .status(SessionSeatStatusDocument.fromString(domain.getStatus().name()))
                .build();
    }

    public SessionSeatDomain toDomain(SessionSeatDocument entity) {
        return SessionSeatDomain.builder()
                .sessionSeatId(SessionSeatIdConverter.fromCompositeKey(entity.getId()))
                .performanceSessionId(new PerformanceSessionId(entity.getPerformanceSessionId()))
                .sessionSeatId(new SessionSeatId(entity.getSeatId(), entity.getPerformanceId()))
                .status(SessionSeatStatus.fromString(entity.getStatus().name()))
                .build();
    }

    public static class SessionSeatIdConverter {

        private static final String DELIMITER = "-";

        public static String toCompositeKey(SessionSeatId id) {
            return id.performanceId() + DELIMITER + id.seatId();
        }

        public static SessionSeatId fromCompositeKey(String key) {
            String[] parts = key.split(DELIMITER);
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid composite key format: " + key);
            }
            return new SessionSeatId(Long.parseLong(parts[1]), Long.parseLong(parts[0]));
        }
    }
}
