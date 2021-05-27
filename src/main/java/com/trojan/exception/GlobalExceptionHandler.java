package com.trojan.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName GlobalExceptionHandler
 * @Description TODO
 * @Author Trojan
 * @Date 2021/5/27
 * @Version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseResult validationHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult != null) {
            if (bindingResult.hasErrors()) {
                FieldError fieldError = bindingResult.getFieldError();
                String defaultMessage = fieldError.getDefaultMessage();
                logger.error(e.getMessage(), e);
                return new ResponseResult(ConstantCode.INVALID_PARAM.CODE, defaultMessage);
            }
        } else {
            return new ResponseResult(ConstantCode.INVALID_PARAM.SUCCESS, ConstantCode.INVALID_PARAM.CODE, ConstantCode.INVALID_PARAM.MSG);
        }
        return null;
    }

    @ExceptionHandler
    public ResponseResult globalHandler(Exception e) {
        if (e instanceof BusinessException) {
            return new ResponseResult(1002, "");
        } else if (e instanceof MethodArgumentNotValidException) {
            return validationHandler((MethodArgumentNotValidException) e);
        } else {
            return null;
        }
    }
}
