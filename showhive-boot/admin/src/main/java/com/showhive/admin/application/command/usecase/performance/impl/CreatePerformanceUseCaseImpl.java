package com.showhive.admin.application.command.usecase.performance.impl;

import com.showhive.ShowHiveException;
import com.showhive.admin.application.command.dto.CreatePerformanceDto;
import com.showhive.admin.application.command.usecase.performance.CreatePerformanceUseCase;
import com.showhive.category.domain.Category;
import com.showhive.category.exception.CategoryErrorCode;
import com.showhive.category.exception.CategoryException;
import com.showhive.category.repository.query.CategoryQueryRepository;
import com.showhive.performance.domain.Performance;
import com.showhive.performance.domain.PerformanceCategoryMap;
import com.showhive.performance.repository.command.PerformanceCategoryMapCommandRepository;
import com.showhive.performance.repository.command.PerformanceCommandRepository;
import com.showhive.performance.repository.query.PerformanceCategoryMapQueryRepository;
import com.showhive.venue.domain.Seat;
import com.showhive.venue.domain.Venue;
import com.showhive.venue.exception.VenueErrorCode;
import com.showhive.venue.exception.VenueException;
import com.showhive.venue.repository.query.SeatQueryRepository;
import com.showhive.venue.repository.query.VenueQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Transactional
@Service
@RequiredArgsConstructor
public class CreatePerformanceUseCaseImpl implements CreatePerformanceUseCase {
    private final PerformanceCommandRepository performanceRepository;
    private final VenueQueryRepository venueQueryRepository;
    private final PerformanceCategoryMapCommandRepository categoryPerformanceCommandRepository;
    private final PerformanceCategoryMapQueryRepository categoryPerformanceQueryRepository;
    private final CategoryQueryRepository categoryQueryRepository;
    private final SeatQueryRepository seatQueryRepository;

    @Override
    public void handle(CreatePerformanceDto commandDto) {

        List<Long> categoryIds = commandDto.categoryIds();

        List<Category> categories = categoryQueryRepository.findByIds(categoryIds);

        // 카테고리 검증. 요청 값 길이와 실제 찾은 값 검증
        if (CollectionUtils.isEmpty(categories)
                || categoryIds.size() != categories.size()) {
            throw new CategoryException(CategoryErrorCode.CATEGORY_VALUE_NOT_VALID);
        }

        List<PerformanceCategoryMap> performanceCategoryMaps =
                categoryPerformanceQueryRepository.findByCategoryIds(categoryIds);

        // 카테고리 맵 검증. 값이 있으면 오류임
        if (!CollectionUtils.isEmpty(performanceCategoryMaps)) {
            throw new ShowHiveException("카테고리 앱의 값에 문제가 있습니다. 관리자를 통해 확인하세요.", 400);
        }

        // 공연장 정보 검증
        Venue venue = venueQueryRepository.findById(commandDto.venueId())
                .orElseThrow(() -> new VenueException(VenueErrorCode.VENUE_NOT_FOUND));

        Performance performance = Performance.create(commandDto.title(), venue.getId(),
                commandDto.runningTime().getSeconds(),
                commandDto.ageRating(), commandDto.advantage(), commandDto.performanceInfo(),
                commandDto.bookStartedAt(), commandDto.bookEndedAt()
        );

        List<Seat> venueSeats = seatQueryRepository.findAllByVenueIdWithSeatGrade(venue.getId());

        // TODO 공연장 좌석 정보 저장 MySQL로 할 지 MongoDB로 할 지 고민
        // PerformanceSeat performanceSeat = null;
    }
}
