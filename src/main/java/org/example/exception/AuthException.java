package org.example.exception;

import org.example.utils.ErrorCodeEnum;

/**
 * 数据操作异常（数据库/数据校验/数据不存在等专用）
 * 继承 ApplicationException，属于业务异常分支
 */
public class AuthException extends ApplicationException {

    /**
     * 字符串错误码构造（兼容旧代码）
     */
    public AuthException(String errorCode) {
        super(errorCode);
    }

    /**
     * 字符串错误码 + 自定义消息
     */
    public AuthException(String errorCode, String customMsg) {
        super(errorCode, customMsg);
    }

    /**
     * 枚举错误码构造（推荐）
     */
    public AuthException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum);
    }

    /**
     * 枚举错误码 + 自定义消息（推荐）
     */
    public AuthException(ErrorCodeEnum errorCodeEnum, String customMsg) {
        super(errorCodeEnum, customMsg);
    }

    // ==================== 静态抛异常工具方法 ====================

    /**
     * 静态抛出：字符串错误码
     */
    public static void throwEx(String code) {
        throw new AuthException(code);
    }

    /**
     * 静态抛出：字符串错误码 + 自定义消息
     */
    public static void throwEx(String code, String msg) {
        throw new AuthException(code, msg);
    }

    /**
     * 静态抛出：枚举错误码（推荐）
     */
    public static void throwEx(ErrorCodeEnum codeEnum) {
        throw new AuthException(codeEnum);
    }

    /**
     * 静态抛出：枚举错误码 + 自定义消息（推荐）
     */
    public static void throwEx(ErrorCodeEnum codeEnum, String msg) {
        throw new AuthException(codeEnum, msg);
    }
}