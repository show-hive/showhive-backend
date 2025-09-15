package com.showhive;

import lombok.Getter;

@Getter
public class ShowHiveException extends RuntimeException {

    private final int statusCode;

    public ShowHiveException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
