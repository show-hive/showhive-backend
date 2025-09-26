package com.showhive.category.exception;

import lombok.Getter;

@Getter
public enum CategoryErrorCode {

    //4XX
    CATEGORY_NOT_FOUND(404, "존재하지 않는 카테고리입니다."),
    CATEGORY_DUPLICATED(404, "존재하지 않는 카테고리입니다."),
    CATEGORY_VALUE_NOT_VALID(400, "유효하지 않은 카테고리 정보입니다."),
    CATEGORY_PARENT_NOT_FOUND(400, "존재하지 않는 상위 카테고리입니다."),
    ;

    private final int statusCode;
    private final String message;

    CategoryErrorCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
