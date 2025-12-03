package com.showhive.admin.fixture;

import com.showhive.category.domain.Category;
import com.showhive.performance.domain.Performance;
import com.showhive.performance.domain.PerformanceCategoryId;
import com.showhive.performance.domain.PerformanceCategoryMap;
import com.showhive.performance.repository.command.PerformanceCategoryMapCommandRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PerformanceCategoryGenerator {
    private final List<String> nodes = List.of(
            "뮤지컬"
            , "콘서트"
            , "스포츠"
            , "전시/행사"
            , "클래식/무용"
            , "아동/가족"
            , "연극"
            , "레저/캠핑"
            , "토핑"
            , "MD shop"
            , "랭킹"
            , "오픈예정"
            , "지역별"
            , "공연장"
    );
    @Autowired
    private PerformanceGenerator performanceGenerator;
    @Autowired
    private PerformanceCategoryMapCommandRepository commandRepository;
    @Autowired
    private CategoryGenerator categoryGenerator;

    // 단일 생성
    public PerformanceCategoryMap generatePerformanceCategoryMap() {

        categoryGenerator.generateRootCategory();
        Category nodeCategory = categoryGenerator.generateNodeCategory("테스트 노드 카테고리 밸류");

        Performance performance = performanceGenerator.generatePerformance("센과 치히로의 행방불명");
        PerformanceCategoryMap performanceCategoryMaps =
                PerformanceCategoryMap.create(
                        PerformanceCategoryId.of(performance.getId().getPerformanceId(),
                                nodeCategory.getId().id())
                        , 0
                );

        return commandRepository.savePerformanceCategory(performanceCategoryMaps);
    }

    // 목록 생성
    public List<PerformanceCategoryMap> generatePerformanceCategoryMaps() {

        List<Category> categories = new ArrayList<>();

        categoryGenerator.generateRootCategory();
        // 하위
        nodes.forEach(node -> {
            Category nodeCategory = categoryGenerator.generateNodeCategory("테스트 노드 카테고리 밸류");
            categories.add(nodeCategory);
        });

        Performance performance = performanceGenerator.generatePerformance("센과 치히로의 행방불명");
        List<PerformanceCategoryMap> performanceCategoryMaps =
                categories.stream().map(
                        category ->
                                PerformanceCategoryMap.create(
                                        PerformanceCategoryId.of(performance.getId().getPerformanceId(),
                                                category.getId().id())
                                        , 0
                                )
                ).toList();

        return commandRepository.savePerformanceCategoryList(performanceCategoryMaps);
    }
}
