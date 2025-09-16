package com.showhive.performance.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceSeatId implements Serializable {

    @Column(name = "seat_id")
    private Long seatId;

    @Column(name = "performance_id")
    private Long performanceId;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PerformanceSeatId)) return false;
        PerformanceSeatId that = (PerformanceSeatId) obj;
        return Objects.equals(seatId, that.seatId) &&
                Objects.equals(performanceId, that.performanceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seatId, performanceId);
    }
}
