package com.showhive.performance.domain.vo;

import java.math.BigDecimal;

public record Money(BigDecimal price, String currency) {
    public static Money of(BigDecimal amount, String currency) {
        return new Money(amount, currency);
    }
}
