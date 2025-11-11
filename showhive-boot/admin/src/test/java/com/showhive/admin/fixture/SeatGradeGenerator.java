package com.showhive.admin.fixture;

import com.showhive.venue.entity.SeatGradeEntity;
import com.showhive.venue.repository.command.SeatGradeCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SeatGradeGenerator {

    @Autowired
    private SeatGradeCommandRepository seatGradeCommandRepository;

    public SeatGradeEntity generateSeatGrade(String grade) {
        SeatGradeEntity seatGrade = SeatGradeEntity.create(grade);
        seatGradeCommandRepository.create(seatGrade);
        return seatGrade;
    }
}
