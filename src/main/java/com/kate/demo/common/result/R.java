package com.kate.demo.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.PortableInterceptor.SUCCESSFUL;

import java.util.HashMap;
import java.util.Map;

/**
 * @className：R
 * @description：统一结果类
 * @createTime：2022/5/10 14:15
 * @author：kate_chen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R {
    //定义统一的返回的基本数据格式
    //成功 & 失败
    //包含信息状态码code、状态信息message、和状态数据data
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    //为了防止外部函数调用该类,将进行构造函数私有化
    //而自身调用，则用到静态函数调用
    //通过类的内部进行调用
    /*
     * @name: ok
     * @description: 返回成功的结果
     */
    private R() {
    }

    public static R ok() {
        R r = new R();
        r.setCode(ResponseEnum.SUCCESS.getCode());
        r.setMessage(ResponseEnum.SUCCESS.getMessage());
        return r;
    }

    /*
     * @name: error
     * @description: 返回失败的结果
     */
    public static R error() {
        R r = new R();
        r.setCode(ResponseEnum.ERROR.getCode());
        r.setMessage(ResponseEnum.ERROR.getMessage());
        return r;
    }

    /*
     * @name:
     * @description: 设置特定的枚举值
     */
    public static R setResult(ResponseEnum responseEnum) {
        R r = new R();
        r.setCode(responseEnum.getCode());
        r.setMessage(responseEnum.getMessage());
        return r;
    }


}
