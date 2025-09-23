package com.showhive.venue;

import com.showhive.common.CursorPage;
import com.showhive.venue.domain.SeatGrade;
import com.showhive.venue.query.SeatGradeQueryJpaRepository;
import com.showhive.venue.repository.query.SeatGradeQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SeatGradeQueryRepositoryImpl implements SeatGradeQueryRepository {

    private final SeatGradeQueryJpaRepository seatGradeQueryJpaRepository;

    @Override
    public Optional<SeatGrade> findById(long seatGradeId) {
        return seatGradeQueryJpaRepository.findById(seatGradeId);
    }

    @Override
    public CursorPage<SeatGrade> findAllByLessThanId(long lastGradeId, int pageSize) {
        Slice<SeatGrade> slice = seatGradeQueryJpaRepository.findAllByLessThanId(lastGradeId,
                PageRequest.of(0, pageSize));
        return new CursorPage<>(
                slice.getContent(),
                slice.hasNext()
        );
    }
}
