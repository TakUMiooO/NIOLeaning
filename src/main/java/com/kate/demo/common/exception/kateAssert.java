package com.kate.demo.common.exception;

import com.kate.demo.common.result.ResponseEnum;
import junit.framework.Assert;
import lombok.extern.slf4j.Slf4j;

/**
 * @className：kateAssert
 * @description：TODO
 * @createTime：2022/5/10 16:48
 * @author：kate_chen
 */
@Slf4j
public class kateAssert extends Assert {
    /*
     * @name: notNull
     * @description: TODO 断言对象不为空！
     * @date: 2022/5/10 16:53
     * @auther: Asuma
    */
    public static void notNull(Object obj, ResponseEnum responseEnum) {
        if (obj == null) {
            log.info("obj is not null");
            throw new kateException(responseEnum);
        }
    }
}
