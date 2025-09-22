package com.showhive.venue;

import com.showhive.common.PageResult;
import com.showhive.venue.domain.SeatGrade;
import com.showhive.venue.query.SeatGradeQueryJpaRepository;
import com.showhive.venue.repository.query.SeatGradeQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SeatGradeQueryRepositoryImpl implements SeatGradeQueryRepository {

    private final SeatGradeQueryJpaRepository seatGradeQueryJpaRepository;

    @Override
    public PageResult<SeatGrade> getList(SeatGrade seatGrade) {
        return null;
    }

    @Override
    public Optional<SeatGrade> findById(long seatGradeId) {
        return seatGradeQueryJpaRepository.findById(seatGradeId);
    }

    @Override
    public List<SeatGrade> findAll() {
        return seatGradeQueryJpaRepository.findAll();
    }
}
