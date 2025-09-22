package com.showhive.admin.application.command.usecase.venue.impl;

import com.showhive.admin.application.command.dto.SeatGradeDto;
import com.showhive.admin.application.command.usecase.venue.ListSeatGradeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListSeatGradeUseCaseImpl implements ListSeatGradeUseCase {
//    private final SeatGradeQueryRepository queryRepository;

    @Override
    public void handle(SeatGradeDto commandDto) {
//        queryRepository.getList(null);
    }
}
