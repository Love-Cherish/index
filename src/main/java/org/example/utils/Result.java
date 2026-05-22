package org.example.utils;

import lombok.Data;

/**
 * 全局统一返回结果
 * @param <T> 数据泛型
 */
@Data
public class Result<T> {

    private Boolean success;
    /**
     * 响应码：0成功，其他表示失败
     */
    private String code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 业务数据
     */
    private T data;

    // ==================== 构造方法 ====================
    public Result() {
    }

    public Result(Boolean success ,String code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message= message;
        this.data = data;
    }

    // ==================== 成功返回 ====================
    public static <T> Result<T> success() {
        return new Result<>(Boolean.TRUE,"200", "操作成功", null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(Boolean.TRUE,"200", "操作成功", data);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(Boolean.TRUE,"200", msg, data);
    }

    // ==================== 失败返回 ====================
    public static <T> Result<T> fail(String code, String message) {
        return new Result<>(Boolean.FALSE,code, message, null);
    }

    public static <T> Result<T> fail(String code, String message, T data) {
        return new Result<>(Boolean.FALSE,code, message, data);
    }

    public static <T> Result<T> fail(ErrorCodeEnum errorCode) {
        return new Result<>(Boolean.FALSE,errorCode.getCode(), errorCode.getMsg(), null);
    }

    public static <T> Result<T> fail(ErrorCodeEnum errorCode, T data) {
        return new Result<>(Boolean.FALSE,errorCode.getCode(), errorCode.getMsg(), data);
    }
}