package com.trojan.controller.to;/**
 * @Description
 * @Author Trojan
 * @Date 2021/5/26
 * @Version 1.0
 */


import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @ClassName FindUserByIdRequest
 * @Description TODO
 * @Author Trojan
 * @Date 2021/5/26
 * @Version 1.0
 */
public class FindUserByIdRequest {
    @NotNull(message = "id不能为空")
    public Integer id;

}
