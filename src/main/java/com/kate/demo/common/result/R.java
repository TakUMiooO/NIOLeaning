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
     * @description: TODO 返回成功的结果
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
     * @description: TODO 返回失败的结果
     */
    public static R error() {
        R r = new R();
        r.setCode(ResponseEnum.ERROR.getCode());
        r.setMessage(ResponseEnum.ERROR.getMessage());
        return r;
    }

    /*
     * @name:
     * @description: TODO 设置特定的枚举值
     */
    public static R setResult(ResponseEnum responseEnum) {
        R r = new R();
        r.setCode(responseEnum.getCode());
        r.setMessage(responseEnum.getMessage());
        return r;
    }

    //上面是为了当将程序运行结果返回出统一的状态码
    //下面的代码是为了将程序运行结果的数据返回给统一返回结果

    /*
     * @name: data
     * @description: TODO 设置特定的返回数据
    */
    public  R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
    //@description: TODO data方法的重载
    public  R data(Map<String,Object> map) {
        this.setData(map);
        return this;
    }
    /*
     * @name:
     * @description: TODO 设置特定的响应消息
    */
    public R message(String message){
        this.setMessage(message);
        return this;
    }

    /*
     * @name:
     * @description: TODO 设置特定的响应码
     */
    public R code(Integer code){
        this.setCode(code);
        return this;
    }


}
