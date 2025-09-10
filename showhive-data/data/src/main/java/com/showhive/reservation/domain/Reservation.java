package com.showhive.reservation.domain;

import com.showhive.BaseEntity;
import com.showhive.member.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "reservations")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // TODO: Code 연결
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "code_id")
//    private Code reservationStatusCode;

    // TODO: PerformanceSession 연결
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "session_id")
//    private PerformanceSession performanceSession;
}
