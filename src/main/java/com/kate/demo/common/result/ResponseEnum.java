package com.kate.demo.common.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @className：ResponseEnum
 * @description：统一返回结果的枚举类
 * @createTime：2022/5/10 13:44
 * @author：kate_chen
 */
@Getter
@ToString
@AllArgsConstructor
public enum ResponseEnum {

    SUCCESS(0,"成功"),
    ERROR(-1,"服务器内部错误");

    //响应状态码
    private Integer code;
    //响应信息
    private String message;
}
