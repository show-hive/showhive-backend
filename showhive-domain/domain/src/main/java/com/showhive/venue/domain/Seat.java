package com.showhive.venue.domain;

import com.showhive.venue.entity.SeatType;
import com.showhive.venue.exception.SeatErrorCode;
import com.showhive.venue.exception.SeatException;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 좌석 정보
 */
@EqualsAndHashCode
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat {
    private SeatId id;
    private String column;
    private Short row;
    private Short floor;
    private SeatGrade grade;
    private SeatType type;
    private VenueId venueId;

    public static Seat create(VenueId venueId,
                              String column,
                              Short row,
                              Short floor,
                              SeatType type,
                              SeatGrade grade) {
        validate(venueId, column, row, floor, type, grade);

        return Seat.builder()
                .venueId(venueId)
                .column(column)
                .row(row)
                .floor(floor)
                .type(type)
                .grade(grade)
                .build();
    }

    /**
     * 도메인 규칙 검증
     */
    private static void validate(VenueId venueId, String column, Short row, Short floor,
                                 SeatType type, SeatGrade grade) {
        if (venueId == null || venueId.getVenueId() == null) {
            throw new SeatException(SeatErrorCode.VENUE_NOT_FOUND);
        }
        if (column == null || column.isBlank()) {
            throw new SeatException(SeatErrorCode.SEAT_COLUMN_INVALID);
        }
        if (row == null || row <= 0) {
            throw new SeatException(SeatErrorCode.SEAT_ROW_INVALID);
        }
        if (floor == null || floor < 0) {
            throw new SeatException(SeatErrorCode.SEAT_FLOOR_INVALID);
        }
        if (type == null) {
            throw new SeatException(SeatErrorCode.SEAT_TYPE_NOT_VALID);
        }
        if (grade == null) {
            throw new SeatException(SeatErrorCode.SEAT_GRADE_NOT_VALID);
        }
    }

    public void deactivate() {
        this.type = SeatType.STANDING; // 예시: 비활성화시 특정 타입 전환
    }

    public boolean isInVenue(Venue venue) {
        return Objects.equals(this.getVenueId(), venue.getId());
    }
}
