package com.trojan.controller.to;/**
 * @Description
 * @Author Trojan
 * @Date 2021/5/26
 * @Version 1.0
 */

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * @ClassName FindUserByIdRequest
 * @Description TODO
 * @Author Trojan
 * @Date 2021/5/26
 * @Version 1.0
 */
public class FindUserByIdResponse {
    @NotNull(message = "id不能为空")
    public Integer id;


}
