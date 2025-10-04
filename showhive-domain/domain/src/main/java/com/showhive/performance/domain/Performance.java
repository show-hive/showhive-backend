package com.showhive.performance.domain;

import com.showhive.BaseEntity;
import com.showhive.performance.exception.PerformanceErrorCode;
import com.showhive.performance.exception.PerformanceException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Table(name = "performances")
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Performance extends BaseEntity {

    @Column(name = "performance_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Long venueId;

    @Column(name = "running_time")
    private Long runningTime;

    @Column(name = "age")
    private Short ageRating;

    private String advantage;

    private String performanceInfo;

    private LocalDateTime bookStartedAt;

    private LocalDateTime bookEndedAt;

    @Enumerated(EnumType.STRING)
    private PerformanceStatus status;


    public static Performance create(String title, Long venueId, Long runningTime, Short ageRating,
                                     String advantage, String performanceInfo, LocalDateTime bookStartedAt,
                                     LocalDateTime bookEndedAt) {
        bookStartedAt = bookStartedAt.withSecond(0).withNano(0);
        bookEndedAt = bookEndedAt.withSecond(0).withNano(0);
        if (bookStartedAt.isAfter(bookEndedAt)) {
            throw new PerformanceException(PerformanceErrorCode.BOOKED_TIME_NOT_VALID);
        }

        PerformanceStatus status = PerformanceStatus.READY;

        if (bookStartedAt.isAfter(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))) {
            status = PerformanceStatus.ONGOING;
        }

        return Performance.builder()
                .title(title)
                .venueId(venueId)
                .runningTime(runningTime)
                .ageRating(ageRating)
                .advantage(advantage)
                .performanceInfo(performanceInfo)
                .bookStartedAt(bookStartedAt)
                .bookEndedAt(bookEndedAt)
                .status(status)
                .build();
    }

    @Getter
    @RequiredArgsConstructor
    public enum PerformanceStatus {
        READY("예매 정보 및 회차 세팅 완료. 오픈 준비 중"),
        ONGOING("예매 진행 중"),
        SOLD_OUT("전 회차 매진"),
        FINISHED("전 회차가 종료"),
        CLOSED("예매 종료"),
        CANCELED("주최 측 사정으로 공연 취소 됨"),
        POSTPONED("공연이 연기"),
        ARCHIVED("끝난 공연을 이력 보관용으로 유지"),
        ;

        private final String description;
    }
}
