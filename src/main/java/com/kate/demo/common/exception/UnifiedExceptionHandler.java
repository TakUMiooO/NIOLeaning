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

}
