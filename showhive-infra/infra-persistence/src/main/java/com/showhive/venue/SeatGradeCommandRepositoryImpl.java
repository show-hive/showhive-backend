package com.showhive.venue;

import com.showhive.venue.command.SeatGradeCommandJpaRepository;
import com.showhive.venue.entity.SeatGradeEntity;
import com.showhive.venue.repository.command.SeatGradeCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SeatGradeCommandRepositoryImpl implements SeatGradeCommandRepository {

    private final SeatGradeCommandJpaRepository commandJpaRepository;

    @Override
    public void create(SeatGradeEntity seatGrade) {
        commandJpaRepository.save(seatGrade);
    }

    @Override
    public boolean existsById(long seatGradeId) {
        return commandJpaRepository.existsById(seatGradeId);
    }

    @Override
    public void deleteById(long seatGradeId) {
        commandJpaRepository.deleteById(seatGradeId);
    }
}
