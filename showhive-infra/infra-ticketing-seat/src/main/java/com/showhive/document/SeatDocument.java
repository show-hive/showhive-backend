package com.showhive.document;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collation = "seat")
public class SeatDocument {
    @Id
    private ObjectId id;
    private Long seatId;
    private Short row;
    private String column;
    private Short floor;
    private String type;
    private SeatGradeDocument seatGrade;

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SeatGradeDocument {
        private Long id;
        private String grade;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
