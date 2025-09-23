package com.showhive.admin.application.command.usecase.seatgrade;

import com.showhive.admin.interfaces.performance.dto.SeatGradeListResponse;
import com.showhive.admin.interfaces.performance.dto.SeatGradeResponse;

public interface ReadSeatGradeUseCase {

    SeatGradeListResponse readAllSeatGrades(int pageSize, long lastGradeId);

    SeatGradeResponse readSeatGrade(long seatGradeId);
}
