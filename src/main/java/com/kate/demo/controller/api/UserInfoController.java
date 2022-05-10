package com.kate.demo.controller.api;

import com.kate.demo.common.exception.kateAssert;
import com.kate.demo.common.exception.kateException;
import com.kate.demo.common.result.R;
import com.kate.demo.common.result.ResponseEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className：UserInfoController
 * @description：TODO
 * @createTime：2022/5/10 09:10
 * @author：kate_chen
 */
@Slf4j
@RestController
@RequestMapping("/test")
@Tags(@Tag(name = "UserInfoController", description = "用户信息"))
public class UserInfoController {

    @Operation(summary = "测试统一返回值getString")
    @GetMapping("/getString")
    public R getString() {
        //为什么可以一直连点下去，因为每个方法的返回值都是同一个R
        //所以连点都在同一个R上，并且每个方法都会完成
        log.info("成功提取了数据，成功返回统一返回值结果");
        return R.ok().data("getString", "统一返回值的结果！！！").code(888888888).message("统一返回值设置了特定的返回消息");
    }

    @Operation(summary = "测试统一异常返回")
    @GetMapping("/getError")
    public R getError() {

        boolean flag = true;
        Object obj= null;
        //可以利用断言assert简化if()语句
//        if (flag) {
//            throw new kateException(ResponseEnum.KATE_DEFINE_EXCEPTION_ERROR);
//        }

        //断言方式Assert来抛出异常，起到简化作用
        kateAssert.notNull(obj,ResponseEnum.KATE_DEFINE_ASSERT_ERROR);


        return R.error().message("测试统一异常返回");
    }

}
