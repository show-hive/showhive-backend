package com.showhive.venue;

import com.showhive.common.CursorPage;
import com.showhive.venue.entity.Direction;
import com.showhive.venue.entity.SeatGrade;
import com.showhive.venue.query.SeatGradeQueryJpaRepository;
import com.showhive.venue.repository.query.SeatGradeQueryRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SeatGradeQueryRepositoryImpl implements SeatGradeQueryRepository {

    private final SeatGradeQueryJpaRepository seatGradeQueryJpaRepository;

    @Override
    public Optional<SeatGrade> findById(long seatGradeId) {
        return seatGradeQueryJpaRepository.findById(seatGradeId);
    }

    @Override
    public CursorPage<SeatGrade> findSeatGradesBy(long lastGradeId, int pageSize, String keyword, Direction direction) {
        if (direction == Direction.DESC) {
            Slice<SeatGrade> slice = seatGradeQueryJpaRepository.findSliceByKeywordAndIdLessThan(lastGradeId,
                    PageRequest.of(0, pageSize), keyword);
            return new CursorPage<>(
                    slice.getContent(),
                    slice.hasNext()
            );
        }
        Slice<SeatGrade> slice = seatGradeQueryJpaRepository.findSliceByKeywordAndIdGreaterThan(lastGradeId,
                PageRequest.of(0, pageSize), keyword);
        return new CursorPage<>(
                slice.getContent(),
                slice.hasNext()
        );
    }
}
