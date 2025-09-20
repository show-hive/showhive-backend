package com.showhive.common;

import org.springframework.data.domain.Page;

public class PageConverter {
    public static <T> PageResult<T> fromSpringDataPage(Page<T> page) {
        return new PageResult<>(
                page.getContent(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getSize()
        );
    }
}
