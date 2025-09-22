package com.showhive.admin.application.command.usecase.seatgrade;

import com.showhive.admin.application.command.dto.SeatGradeDto;

public interface UpdateSeatGradeUseCase {
    void update(long seatGradeId, SeatGradeDto commandDto);
}
