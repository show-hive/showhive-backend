package com.showhive.performance.entity;

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
public class PerformanceCategoryEntityId implements Serializable {

    @Column(name = "performance_id")
    private Long performanceId;

    @Column(name = "category_id")
    private Long categoryId;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PerformanceCategoryEntityId)) {
            return false;
        }
        PerformanceCategoryEntityId that = (PerformanceCategoryEntityId) obj;
        return Objects.equals(performanceId, that.performanceId) &&
                Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(performanceId, categoryId);
    }
}
