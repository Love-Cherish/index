package org.example.utils;

import lombok.Getter;

@Getter
public enum ErrorCodeEnum {

    SUCCESS("200", "操作成功"),
    PARAM_VALID_ERROR("A0400", "参数校验异常"),
    DB_OP_ERROR("B0001", "数据库操作异常"),
    SYSTEM_ERROR("500", "系统异常"),
    AUTH_ERROR("401", "认证失败"),
    ;

    private final String code;
    private final String msg;

    ErrorCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}