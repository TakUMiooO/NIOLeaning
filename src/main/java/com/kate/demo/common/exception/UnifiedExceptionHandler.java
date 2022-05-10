package com.kate.demo.common.exception;

import com.kate.demo.common.result.R;
import com.kate.demo.common.result.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @className：UnifiedExceptionHandlar
 * @description：统一返回异常处理方案,AOP切面编程
 * @createTime：2022/5/10 11:00
 * @author：kate_chen
 */
@Slf4j
@RestControllerAdvice //该注解就是异常的AOP切面  这个注解让所有response都是以responseBody返回
public class UnifiedExceptionHandler {


    //controller所有发生的异常都会走到这里
    @ExceptionHandler(value = Exception.class)
    public R handleException(Exception e) {
        log.error(e.getMessage(), e);
        return R.error();
    }

    //为了处理具体的异常信息，并提示具体的异常信息结果
    //这里有个sql语法错误的，具体错误抛出
    @ExceptionHandler(value = BadSqlGrammarException.class)
    public R handleException(BadSqlGrammarException e){
        log.error(e.getMessage(),e);
        return R.setResult(ResponseEnum.BAD_SQL_GRAMMAR_ERROR);
    }
    //第一种异常报错，太宽泛，就算报错了 也不知道发生了什么异常
    //而第二种异常报错，太复杂，如果每种异常都需要自己详细定义，是不合理的
    //有没有简洁的异常定义呢？那就是自定义异常！
    //目标使用一个或较少的异常类，可以捕获和显示所有的异常信息
    //创建一个自定义的异常类，在程序中抛出这个自定义异常对象(必须是运行时异常)，并在统一异常处理器种捕获自定义异常对象




}
