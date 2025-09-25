package com.showhive.admin.application.command.usecase.venue.seatgrade;

import com.showhive.admin.interfaces.venue.dto.SeatGradeListResponse;
import com.showhive.admin.interfaces.venue.dto.SeatGradeResponse;

public interface ReadSeatGradeUseCase {

    SeatGradeListResponse readAllSeatGrades(int pageSize, long lastGradeId, String keyword, String direction);

    SeatGradeResponse readSeatGrade(long seatGradeId);
}
