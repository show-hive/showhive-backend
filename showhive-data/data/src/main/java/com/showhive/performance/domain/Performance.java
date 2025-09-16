package com.showhive.performance.domain;

import com.showhive.BaseEntity;
import com.showhive.ShowHiveException;
import com.showhive.performance.exception.PerformanceErrorCode;
import com.showhive.performance.exception.PerformanceException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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


    public static Performance create(String title, Long venueId, Long runningTime, Short ageRating,
                                     String advantage, String performanceInfo, LocalDateTime bookStartedAt,
                                     LocalDateTime bookEndedAt) {
        if (bookStartedAt.isAfter(bookEndedAt)) {
            throw new PerformanceException(PerformanceErrorCode.BOOKED_TIME_NOT_VALID);
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
                .build();
    }
}
