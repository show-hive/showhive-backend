package com.showhive.performance.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceCategoryId implements Serializable {

    @Column(name = "performance_id")
    private Long performanceId;

    @Column(name = "code_id")
    private Long codeId;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PerformanceCategoryId)) return false;
        PerformanceCategoryId that = (PerformanceCategoryId) obj;
        return Objects.equals(performanceId, that.performanceId) &&
                Objects.equals(codeId, that.codeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(performanceId, codeId);
    }
}
