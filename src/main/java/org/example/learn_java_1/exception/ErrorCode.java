package org.example.learn_java_1.exception;

public enum ErrorCode {
    USER_EXISTED(1001, "user existed"),
    USER_NOT_FOUND(1002, "user not found"),
    UNCATEGORIZED_EXCEPTION(9999, "uncategorized exception"),
    INVALID_ARGUMENT(10000, "INVALID_ARGUMENT"),
    INVALID_MESSAGE_KEY(1004, "INVALID_ARGUMENT"),
    MIN_PASSWORD(1003, "password must be at least 4 characters");
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
