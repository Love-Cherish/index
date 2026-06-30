package org.example.exception;

import org.example.utils.ErrorCodeEnum;

/**
 * 数据操作异常（数据库/数据校验/数据不存在等专用）
 * 继承 ApplicationException，属于业务异常分支
 */
public class ExcelTemplateException extends ApplicationException {

    /**
     * 字符串错误码构造（兼容旧代码）
     */
    public ExcelTemplateException(String errorCode) {
        super(errorCode);
    }

    /**
     * 字符串错误码 + 自定义消息
     */
    public ExcelTemplateException(String errorCode, String customMsg) {
        super(errorCode, customMsg);
    }

    /**
     * 枚举错误码构造（推荐）
     */
    public ExcelTemplateException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum);
    }

    /**
     * 枚举错误码 + 自定义消息（推荐）
     */
    public ExcelTemplateException(ErrorCodeEnum errorCodeEnum, String customMsg) {
        super(errorCodeEnum, customMsg);
    }

    // ==================== 静态抛异常工具方法 ====================

    /**
     * 静态抛出：字符串错误码
     */
    public static ExcelTemplateException throwEx(String code) {
        return new ExcelTemplateException(code);
    }

    /**
     * 静态抛出：字符串错误码 + 自定义消息
     */
    public static ExcelTemplateException throwEx(String code, String msg) {
        return new ExcelTemplateException(code, msg);
    }

    /**
     * 静态抛出：枚举错误码（推荐）
     */
    public static ExcelTemplateException throwEx(ErrorCodeEnum codeEnum) {
        return new ExcelTemplateException(codeEnum);
    }

    /**
     * 静态抛出：枚举错误码 + 自定义消息（推荐）
     */
    public static ExcelTemplateException ExcelTemplateException(ErrorCodeEnum codeEnum, String msg) {
        return new ExcelTemplateException(codeEnum, msg);
    }
}