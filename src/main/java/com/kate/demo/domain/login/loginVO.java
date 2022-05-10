package com.kate.demo.domain.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className：loginVO
 * @description：用户登录界面实体类
 * @createTime：2022/5/10 08:52
 * @author：kate_chen
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "loginVO",description = "用户登录界面实体类")
public class loginVO {

    @Schema(name = "userType",description = "用户类型")
    private Integer userType;
    @Schema(name = "mobileNumber",description = "手机号")
    private String mobile;
    @Schema(name = "password",description = "密码")
    private String password;

}
