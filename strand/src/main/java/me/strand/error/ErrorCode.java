package me.strand.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_POST_FORMAT("20000"),
    INVALID_COMMENT_FORMAT("20001"),
    COMMENT_INSERTION_FAILED("20002"),

    CLASS_NOT_SUPPORTED("50000"),

    UNKNOWN_KAFKA_ERROR("60000"),

    TEMPORARY_CODE("99999");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }
}
