package com.kate.demo.controller.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.extern.slf4j.Slf4j;
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

}
