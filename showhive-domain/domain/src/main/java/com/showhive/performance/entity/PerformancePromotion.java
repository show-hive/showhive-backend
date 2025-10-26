package com.showhive.performance.entity;

import com.showhive.BaseEntity;
import com.showhive.file.domain.File;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@Table(name = "performance_promotions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PerformancePromotion extends BaseEntity {

    @Id
    @Column(name = "promotion_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id", nullable = false)
    private Performance performance;

    // File 엔티티와 ManyToOne 관계 (아이콘)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "icon_id")
    private File icon;

    private String description;
}
