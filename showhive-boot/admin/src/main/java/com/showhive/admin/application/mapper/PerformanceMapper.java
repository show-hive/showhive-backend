package com.showhive.admin.application.mapper;

import com.showhive.admin.application.command.dto.CreatePerformanceDto;
import com.showhive.performance.domain.Performance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PerformanceMapper {
    PerformanceMapper INSTANCE = Mappers.getMapper(PerformanceMapper.class);

    @Mapping(target = "id", ignore = true)
    Performance create(CreatePerformanceDto request);
}
