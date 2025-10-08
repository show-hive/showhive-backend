package com.showhive.performance.entity;

import com.showhive.BaseEntity;
import com.showhive.category.domain.CategoryDomain;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "performance_category_maps")
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PerformanceCategoryMap extends BaseEntity {

    @EmbeddedId
    private PerformanceCategoryId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("performanceId")
    @JoinColumn(name = "performance_id")
    private Performance performance;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    private CategoryDomain categoryCategory;

    @Column(name = "priority")
    private Integer priority;
}
