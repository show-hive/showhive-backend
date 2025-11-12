package com.showhive.performance.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PerformanceStatus {
    READY("예매 정보 및 회차 세팅 완료. 오픈 준비 중"),
    ONGOING("예매 진행 중"),
    SOLD_OUT("전 회차 매진"),
    FINISHED("전 회차가 종료"),
    CLOSED("예매 종료"),
    CANCELED("주최 측 사정으로 공연 취소 됨"),
    POSTPONED("공연이 연기"),
    ARCHIVED("끝난 공연을 이력 보관용으로 유지"),
    ;

    private final String description;
}
