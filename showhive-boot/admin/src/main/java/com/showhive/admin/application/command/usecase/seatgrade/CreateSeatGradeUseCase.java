package com.showhive.admin.application.command.usecase.seatgrade;

import com.showhive.admin.application.command.dto.SeatGradeDto;

public interface CreateSeatGradeUseCase {
    void handle(SeatGradeDto commandDto);
}
