package com.showhive.exception;

import lombok.Getter;

@Getter
public abstract class ShowHiveException extends RuntimeException {

    private final int statusCode;

    protected ShowHiveException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
