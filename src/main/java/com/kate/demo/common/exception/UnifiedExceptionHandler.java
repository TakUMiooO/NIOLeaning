package com.kate.demo.common.exception;

import com.kate.demo.common.result.R;
import com.kate.demo.common.result.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

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
    //首先先定义一个新的自定义异常kateException
    //将自定义的异常放入这个统一异常管理类，当扫描到异常后，自动定位在这里
    @ExceptionHandler(value = kateException.class)
    public R handleException(kateException e){
        log.error(e.getMessage(), e);
        return R.error().message(e.getMessage()).code(e.getCode());
    }

    /**
     * Controller上一层相关异常
     * 比如前端给的参数就是错误，tomcat直接就报错了
     * 根本不是后端的问题
     * 所以需要直接自定义把所有的错误进行细化
     */
    @ExceptionHandler({
            NoHandlerFoundException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            MethodArgumentNotValidException.class,
            HttpMediaTypeNotAcceptableException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            MissingServletRequestPartException.class,
            AsyncRequestTimeoutException.class
    })
    public R handleServletException(Exception e) {
        log.error(e.getMessage(), e);
        //SERVLET_ERROR(-102, "servlet请求异常"),
        return R.error().message(ResponseEnum.SERVLET_ERROR.getMessage()).code(ResponseEnum.SERVLET_ERROR.getCode());
    }


}
