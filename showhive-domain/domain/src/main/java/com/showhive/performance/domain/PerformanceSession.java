package com.showhive.performance.domain;

import com.showhive.performance.domain.vo.Money;
import com.showhive.performance_seat.domain.PerformanceSeatPrice;
import com.showhive.venue.domain.SeatGrade;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 공연 회차
 */
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PerformanceSession {
    private PerformanceSessionId id;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<Casting> castings = new ArrayList<>();
    private List<PerformanceSeatPrice> gradePrices = new ArrayList<>();

    public void addCasting(Casting casting) {
        castings.add(casting);
    }

    public void setGradePrice(SeatGrade grade, Money price) {
        gradePrices.add(new PerformanceSeatPrice(grade, price));
    }

    public Money getPriceForGrade(SeatGrade grade) {
        return gradePrices.stream()
                .filter(g -> g.grade().equals(grade))
                .findFirst()
                .map(PerformanceSeatPrice::price)
                .orElseThrow(() -> new IllegalArgumentException("가격이 설정되지 않은 등급입니다."));
    }
}
