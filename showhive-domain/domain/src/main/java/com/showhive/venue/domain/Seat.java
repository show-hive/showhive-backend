package com.showhive.venue.domain;

import com.showhive.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "seats")
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id")
    private Venue venue;

    private String seatColumn;

    private Short seatRow;

    private Short seatFloor;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @ManyToOne
    @JoinColumn(name = "seat_grade_id")
    private SeatGrade seatGrade;

    public static Seat create(Venue venue, String seatColumn,
                              Short seatRow, Short seatFloor,
                              SeatType seatType, SeatGrade seatGrade) {
        return Seat.builder()
                .venue(venue)
                .seatColumn(seatColumn)
                .seatRow(seatRow)
                .seatFloor(seatFloor)
                .seatType(seatType)
                .seatGrade(seatGrade)
                .build();
    }
}
