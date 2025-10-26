package com.showhive.document;

import java.util.Arrays;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collation = "session_seat")
public class SessionSeatDocument {
    @Id
    private String id;

    private Long seatId;
    private Long performanceId;
    private Long performanceSessionId;
    private SessionSeatStatusDocument status;


    @Getter
    @RequiredArgsConstructor
    public enum SessionSeatStatusDocument {
        AVAILABLE("사용 가능"),
        HOLD("예매 중"),
        SOLD("예매 완료"),
        ;

        private final String description;

        public static SessionSeatStatusDocument fromString(String description) {
            return Arrays.stream(SessionSeatStatusDocument.values())
                    .filter(sessionSeatStatusDocument ->  sessionSeatStatusDocument.name().equals(description))
                    .findFirst()
                    .orElse(SessionSeatStatusDocument.AVAILABLE);
        }
    }
}
