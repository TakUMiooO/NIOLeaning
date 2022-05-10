package com.kate.demo.controller.api;

import com.kate.demo.common.result.R;
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
    public R getString(){
        return R.ok().data("getString","统一返回值的结果！！！");
    }

}
