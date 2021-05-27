package com.trojan.exception;/**
 * @Description
 * @Author Trojan
 * @Date 2021/5/27
 * @Version 1.0
 */

/**
 * @InterfaceName Response
 * @Description TODO
 * @Author Trojan
 * @Date 2021/5/27
 * @Version 1.0
 */
public interface ResponseCode {
    //操作是否成功,true为成功，false操作失败
    boolean success();

    //操作代码
    int code();

    //提示信息
    String message();
}
