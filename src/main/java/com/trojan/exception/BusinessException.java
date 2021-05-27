package com.trojan.exception;/**
 * @Description
 * @Author Trojan
 * @Date 2021/5/28
 * @Version 1.0
 */

/**
 * @ClassName BusinessException
 * @Description TODO
 * @Author Trojan
 * @Date 2021/5/28
 * @Version 1.0
 */
public class BusinessException extends Exception {

    public BusinessException() {

    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
