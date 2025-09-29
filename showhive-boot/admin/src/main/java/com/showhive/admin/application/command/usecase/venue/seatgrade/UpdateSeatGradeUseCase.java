package com.showhive.admin.application.command.usecase.venue.seatgrade;

import com.showhive.admin.application.command.dto.venue.SeatGradeDto;

public interface UpdateSeatGradeUseCase {
    void handle(long seatGradeId, SeatGradeDto commandDto);
}
