package com.showhive.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(500, "서버 내부 오류가 발생했습니다."),
    CLIENT_REQUEST_ERROR(400, "잘못된 클라이언트 요청입니다."),
    URL_PARAMETER_ERROR(400, "입력이 잘못되었습니다."),
    METHOD_ARGUMENT_TYPE_MISMATCH(400, "요청 타입이 일치하지 않습니다."),
    ALREADY_DISCONNECTED(400, "클라이언트에서 이미 종료한 요청입니다."),
    METHOD_NOT_SUPPORTED(405, "허용되지 않은 메서드입니다."),
    NO_RESOURCE_FOUND(404, "요청한 리소스를 찾을 수 없습니다."),
    MEDIA_TYPE_NOT_SUPPORTED(415, "허용되지 않은 미디어 타입입니다."),
    EXPIRED_TOKEN(401, "만료된 토큰입니다."),
    INVALID_TOKEN(400, "유효하지 않은 토큰입니다."),
    ;

    private final int statusCode;
    private final String message;

    ErrorCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
