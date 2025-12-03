package com.showhive.admin.fixture;

import com.showhive.performance.domain.Performance;
import com.showhive.performance.mapper.PerformanceMapper;
import com.showhive.performance.repository.command.PerformanceCommandRepository;
import com.showhive.venue.domain.Venue;
import com.showhive.venue.mapper.VenueMapper;
import java.time.Duration;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PerformanceGenerator {

    @Autowired
    private PerformanceCommandRepository performanceCommandRepository;
    @Autowired
    private PerformanceMapper performanceMapper;
    @Autowired
    private VenueMapper venueMapper;

    @Autowired
    private VenueGenerator venueGenerator;

    @Autowired
    private PerformanceCategoryGenerator performanceCategoryGenerator;

    public Performance generatePerformance(String performanceName) {
        Venue venue = venueGenerator.generateVenue("테스트 장소");

        Performance performance = Performance.create(
                performanceName, venue.getId(), Duration.ofHours(2),
                (short) 15, "혜택 없음", "테스트 공연정보 입니다."
                , null, null, LocalDateTime.now().plusDays(20L),
                LocalDateTime.now().plusDays(50L)
        );

        return performanceCommandRepository.savePerformance(performance);
    }
}
