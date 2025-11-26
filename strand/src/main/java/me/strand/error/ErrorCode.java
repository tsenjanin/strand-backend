package me.strand.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_POST_FORMAT("20000"),
    INVALID_COMMENT_FORMAT("20001"),
    CLASS_NOT_SUPPORTED("50000");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }
}
