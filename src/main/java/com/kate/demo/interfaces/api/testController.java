package com.kate.demo.interfaces.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className：testController
 * @description：TODO
 * @createTime：2022/4/24 18:31
 * @author：kate_chen
 */

@RestController
public class testController {


    @GetMapping("/get")
    public String getString(){
        return "11111111";
    }
}
