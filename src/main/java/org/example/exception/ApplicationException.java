package org.example.exception;

import lombok.Getter;
import org.example.utils.ErrorCodeEnum;

/**
 * 业务自定义异常（全局统一业务异常）
 */
@Getter
public class ApplicationException extends RuntimeException {

    /**
     * 错误码
     */
    private final String errorCode;

    /**
     * 错误消息（最终返回给前端的）
     */
    private final String customMsg;

    // ====================== 最常用：直接传错误码枚举 ======================
    public ApplicationException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMsg());
        this.errorCode = errorCodeEnum.getCode();
        this.customMsg = errorCodeEnum.getMsg();
    }

    // ====================== 枚举 + 自定义覆盖消息 ======================
    public ApplicationException(ErrorCodeEnum errorCodeEnum, String customMsg) {
        super(customMsg);
        this.errorCode = errorCodeEnum.getCode();
        this.customMsg = customMsg;
    }

    // ====================== 兼容旧代码：传字符串错误码 ======================
    public ApplicationException(String errorCode) {
        this(errorCode, errorCode);
    }

    public ApplicationException(String errorCode, String customMsg) {
        super(customMsg);
        this.errorCode = errorCode;
        this.customMsg = customMsg;
    }
}