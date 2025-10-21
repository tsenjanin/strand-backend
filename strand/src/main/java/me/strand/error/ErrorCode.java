package me.strand.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    SOME_ERROR("12345");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }
}
