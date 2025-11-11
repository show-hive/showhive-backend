package com.showhive.performance.entity;

import com.showhive.BaseEntity;
import com.showhive.performance.domain.PerformanceStatus;
import com.showhive.performance.exception.PerformanceErrorCode;
import com.showhive.performance.exception.PerformanceException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
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
public class PerformanceEntity extends BaseEntity {

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

    private String posterImageUrl;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "performance_id")
    private List<PerformanceSessionEntity> performanceSessions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "performance_id")
    private List<PerformanceCategoryMapEntity> categories = new ArrayList<>();

    public static PerformanceEntity create(String title, Long venueId, Long runningTime, Short ageRating,
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

        return PerformanceEntity.builder()
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

    public void addPerformanceSession(List<PerformanceSessionEntity> performanceSessionEntities) {
        performanceSessions.addAll(performanceSessionEntities);
    }

    public void addCategory(List<PerformanceCategoryMapEntity> performanceCategoryMapEntities) {
        categories.addAll(performanceCategoryMapEntities);
    }
}
