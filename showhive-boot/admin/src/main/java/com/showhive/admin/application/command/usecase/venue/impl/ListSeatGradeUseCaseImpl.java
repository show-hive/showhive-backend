package com.showhive.admin.application.command.usecase.venue.impl;

import com.showhive.admin.application.command.dto.CreateSeatGradeDto;
import com.showhive.admin.application.command.usecase.venue.ListSeatGradeUseCase;
import com.showhive.venue.repository.query.SeatGradeQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListSeatGradeUseCaseImpl implements ListSeatGradeUseCase {
//    private final SeatGradeQueryRepository queryRepository;

    @Override
    public void handle(CreateSeatGradeDto commandDto) {
//        queryRepository.getList(null);
    }
}
