package com.lsh.demo.bootstone.service;

import lombok.Getter;

@Getter
public enum ErrorEnum {
    /**
     *
     */

    REQUEST_BODY_NULL("1001", "POST请求body体为空"),
    AUTH_ERROR("1002", "认证失败"),
    ;

    private String code;

    private String message;

    ErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
