package org.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.utils.ErrorCodeEnum;
import org.example.utils.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

/**
 * 全局统一异常处理（增强版）
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义业务异常
     */
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Result<?>> handleApplicationException(ApplicationException exception, Locale locale) {
        log.error("【业务异常】code={}，msg={}", exception.getErrorCode(), exception.getCustomMsg(), exception);
        return ResponseEntity.ok(Result.fail(exception.getErrorCode(), exception.getCustomMsg()));
    }

    /**
     * 参数校验异常（@Valid 失败）
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<Result<?>> handleValidException(Exception ex) {
        List<Map<String, String>> errorList = new ArrayList<>();
        List<ObjectError> allErrors;
        if (ex instanceof MethodArgumentNotValidException) {
            allErrors = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
        } else {
            allErrors = ((BindException) ex).getBindingResult().getAllErrors();
        }

        for (ObjectError error : allErrors) {
            Map<String, String> errorMap = new HashMap<>(2);
            if (error instanceof FieldError) {
                errorMap.put("field", ((FieldError) error).getField());
            } else {
                errorMap.put("field", error.getObjectName());
            }
            errorMap.put("message", error.getDefaultMessage());
            errorList.add(errorMap);
        }

        log.error("【参数校验异常】", ex);
        return ResponseEntity.ok(Result.fail(
                ErrorCodeEnum.PARAM_VALID_ERROR.getCode(),
                ErrorCodeEnum.PARAM_VALID_ERROR.getMsg(),
                errorList
        ));
    }

    /**
     * 数据库相关异常
     */
    @ExceptionHandler({
            org.apache.ibatis.exceptions.PersistenceException.class,
            java.sql.SQLException.class,
            org.springframework.dao.DataAccessException.class,
            org.springframework.transaction.TransactionSystemException.class
    })
    public ResponseEntity<Result<?>> handleSqlException(Exception exception) {
        Throwable root = getRootCause(exception);
        log.error("【数据库异常】", root);
        
        // 安全处理：不返回真实SQL异常信息
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Result.fail(ErrorCodeEnum.DB_OP_ERROR.getCode(), "数据库操作失败，请联系管理员"));
    }

    /**
     * 认证异常 401
     */
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<Result<?>> handleAuthException(AuthException e) {
        log.warn("【认证异常】{}", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Result.fail(ErrorCodeEnum.AUTH_ERROR.getCode(), e.getMessage()));
    }

    /**
     * 全局兜底异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result<?>> handleAll(Exception exception) {
        log.error("【系统未知异常】", exception);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Result.fail(ErrorCodeEnum.SYSTEM_ERROR.getCode(), "系统繁忙，请稍后重试"));
    }

    /**
     * 递归获取最底层异常
     */
    private Throwable getRootCause(Throwable throwable) {
        if (throwable.getCause() != null) {
            return getRootCause(throwable.getCause());
        }
        return throwable;
    }
}