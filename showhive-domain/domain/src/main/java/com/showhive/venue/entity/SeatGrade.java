package com.showhive.venue.entity;

import com.showhive.BaseEntity;
import com.showhive.venue.exception.SeatGradeErrorCode;
import com.showhive.venue.exception.SeatGradeException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "seat_grades")
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SeatGrade extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_grade_id")
    private Long id;

    @Column(name = "seat_grade")
    private String grade;

    public static SeatGrade create(String grade) {
        if (grade.isBlank()) {
            throw new SeatGradeException(SeatGradeErrorCode.SEAT_GRADE_NOT_VALID);
        }
        return SeatGrade.builder()
                .grade(grade)
                .build();
    }

    public void changeGrade(String grade) {
        this.grade = grade;
    }
}
