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
import com.showhive.performance.mapper.PerformanceCategoryMapper;
import com.showhive.performance.repository.command.PerformanceCommandRepository;
import com.showhive.performance.repository.query.PerformanceCategoryMapQueryRepository;
import com.showhive.venue.domain.Venue;
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
    private final PerformanceCategoryMapQueryRepository categoryPerformanceQueryRepository;
    private final CategoryQueryRepository categoryQueryRepository;
    private final PerformanceCategoryMapper performanceCategoryMapper;

    @Override
    public void handle(CreatePerformanceDto commandDto) {

        List<Long> reqCategoryIds = commandDto.categoryIds();

        List<Category> categories = categoryQueryRepository.findByIds(reqCategoryIds);

        // 카테고리 검증. 요청 값 길이와 실제 찾은 값 검증
        if (CollectionUtils.isEmpty(categories)
                || reqCategoryIds.size() != categories.size()) {
            throw new CategoryException(CategoryErrorCode.CATEGORY_VALUE_NOT_VALID);
        }

        List<PerformanceCategoryMap> performanceCategoryMaps =
                categoryPerformanceQueryRepository.findByCategoryIds(reqCategoryIds);

        // 카테고리 맵 검증. 값이 있으면 오류임
        if (!CollectionUtils.isEmpty(performanceCategoryMaps)) {
            throw new ShowHiveException("카테고리 값에 문제가 있습니다. 관리자를 통해 확인하세요.", 400);
        }

        // 공연장 정보 검증
        Venue venue = venueQueryRepository.findById(commandDto.venueId());

        Performance performance = Performance.create(commandDto.title(), venue.getId(),
                commandDto.runningTime(),
                commandDto.ageRating(), commandDto.advantage(), commandDto.performanceInfo(),
                commandDto.posterImageUrl(), performanceCategoryMaps, commandDto.bookStartedAt(),
                commandDto.bookEndedAt()
        );

        performanceRepository.savePerformance(performance);
    }
}
