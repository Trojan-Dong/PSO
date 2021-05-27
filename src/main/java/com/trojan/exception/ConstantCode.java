package com.trojan.exception;

/**
 * @Description
 * @Author Trojan
 * @Date 2021/5/27
 * @Version 1.0
 */
public enum ConstantCode implements ResponseCode {
    INVALID_PARAM(false, 10001, "参数缺失");


    public final Integer CODE;
    public final String MSG;
    public final boolean SUCCESS;


    ConstantCode(boolean isSuccess, Integer code, String msg) {
        this.CODE = code;
        this.MSG = msg;
        this.SUCCESS = isSuccess;
    }


    @Override
    public boolean success() {
        return SUCCESS;
    }

    @Override
    public int code() {
        return CODE;
    }

    @Override
    public String message() {
        return MSG;
    }
}
