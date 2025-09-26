package com.showhive.admin.fixture;

import com.showhive.venue.domain.SeatGrade;
import com.showhive.venue.repository.command.SeatGradeCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SeatGradeGenerator {

    @Autowired
    private SeatGradeCommandRepository seatGradeCommandRepository;

    public SeatGrade generateSeatGrade(String grade) {
        SeatGrade seatGrade = SeatGrade.create(grade);
        seatGradeCommandRepository.create(seatGrade);
        return seatGrade;
    }
}
