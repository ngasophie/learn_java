package org.example.learn_java_1.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
@Getter
public enum ErrorCode {
    USER_EXISTED(1001, "user existed", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1002, "user not found", HttpStatus.BAD_REQUEST),
    INVALID_MESSAGE_KEY(1004, "INVALID_ARGUMENT", HttpStatus.BAD_REQUEST),
    MIN_PASSWORD(1003, "password must be at least 4 characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "user not existed", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1007, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNCATEGORIZED_EXCEPTION(9999, "uncategorized exception", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_ARGUMENT(10000, "INVALID_ARGUMENT", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(20000, "unauthorized", HttpStatus.FORBIDDEN);

    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
