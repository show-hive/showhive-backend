package com.showhive.admin.application.command.usecase.seatgrade;

import com.showhive.admin.interfaces.performance.dto.SeatGradeListResponse;

public interface ReadSeatGradeUseCase {

    SeatGradeListResponse readAllSeatGrades();
}
