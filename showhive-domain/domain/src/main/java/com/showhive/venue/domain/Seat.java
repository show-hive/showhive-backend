package com.showhive.venue.domain;

import com.showhive.venue.entity.SeatType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 좌석 정보
 */
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
}
