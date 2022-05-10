package com.kate.demo.common.exception;

import com.kate.demo.common.result.ResponseEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className：kateException
 * @description：TODO 自定义异常
 * @createTime：2022/5/10 16:08
 * @author：kate_chen
 */


//不能继承Exception，因为exception是一个检查异常
//所以要继承一个运行时异常RuntimeException
@Data
@NoArgsConstructor
public class kateException extends RuntimeException{

    private Integer code;
    private String message;
    /**
     *
     * @param message 错误消息
     */
    public kateException(String message) {
        this.message = message;
    }

    /**
     *
     * @param message 错误消息
     * @param code 错误码
     */
    public kateException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    /**
     *
     * @param message 错误消息
     * @param code 错误码
     * @param cause 原始异常对象
     */
    public kateException(String message, Integer code, Throwable cause) {
        super(cause);
        this.message = message;
        this.code = code;
    }

    /**
     *
     * @param resultCodeEnum 接收枚举类型
     */
    public kateException(ResponseEnum resultCodeEnum) {
        this.message = resultCodeEnum.getMessage();
        this.code = resultCodeEnum.getCode();
    }

    /**
     *
     * @param resultCodeEnum 接收枚举类型
     * @param cause 原始异常对象
     */
    public kateException(ResponseEnum resultCodeEnum, Throwable cause) {
        super(cause);
        this.message = resultCodeEnum.getMessage();
        this.code = resultCodeEnum.getCode();
    }
}
