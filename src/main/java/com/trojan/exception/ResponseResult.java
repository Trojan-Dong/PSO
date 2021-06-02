package com.trojan.exception;/**
 * @Description
 * @Author Trojan
 * @Date 2021/5/27
 * @Version 1.0
 */

/**
 * @ClassName Response
 * @Description TODO
 * @Author Trojan
 * @Date 2021/5/27
 * @Version 1.0
 */
public class ResponseResult {
    public Integer code;
    public String msg;
    public boolean success;

    public ResponseResult() {
        this.code = 10000;
        this.msg = "SUCCESS";
        this.success = true;
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(boolean success, Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    private void defaultValue() {

    }
}
