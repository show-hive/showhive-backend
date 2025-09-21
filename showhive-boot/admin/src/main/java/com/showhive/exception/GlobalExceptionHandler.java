package com.showhive.exception;

import com.showhive.ShowHiveException;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.net.BindException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindingException(BindException exception) {
        log.warn("[Binding Exception] occurred", exception);
        return toErrorResponse(ErrorCode.CLIENT_REQUEST_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.warn("[Method Argument Not Valid] occurred", exception);
        return toErrorResponse(ErrorCode.CLIENT_REQUEST_ERROR);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        log.warn("[Method Argument Type Mismatch] occurred", exception);
        return toErrorResponse(ErrorCode.METHOD_ARGUMENT_TYPE_MISMATCH);
    }

    @ExceptionHandler(ClientAbortException.class)
    public ResponseEntity<ErrorResponse> handleClientAbortException(ClientAbortException exception) {
        log.info("Client aborted the request", exception);
        return toErrorResponse(ErrorCode.ALREADY_DISCONNECTED);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        log.warn("[Unsupported HTTP Method Type]", exception);
        return toErrorResponse(ErrorCode.METHOD_NOT_SUPPORTED);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException exception) {
        log.warn("[Unsupported Media Type]", exception);
        return toErrorResponse(ErrorCode.MEDIA_TYPE_NOT_SUPPORTED);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResourceFoundException(NoResourceFoundException exception) {
        log.warn("[No Resource Found]", exception);
        return toErrorResponse(ErrorCode.NO_RESOURCE_FOUND);
    }

    @ExceptionHandler(ShowHiveException.class)
    public ResponseEntity<ErrorResponse> handleShowHiveException(ShowHiveException exception) {
        log.error("[ShowHive Exception]: {}", exception.getMessage(), exception);
        return toErrorResponse(exception.getStatusCode(), exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.error("[Unhandled Exception] {}: {}", exception.getClass().getSimpleName(), exception.getMessage(), exception);
        return toErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> toErrorResponse(ErrorCode errorCode) {
        return toErrorResponse(errorCode.getStatusCode(), errorCode.getMessage());
    }

    private ResponseEntity<ErrorResponse> toErrorResponse(int statusCode, String message) {
        ErrorResponse errorResponse = new ErrorResponse(message);
        return ResponseEntity.status(statusCode)
                .body(errorResponse);
    }
}
