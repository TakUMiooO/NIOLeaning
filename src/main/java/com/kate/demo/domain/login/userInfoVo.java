package com.kate.demo.domain.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className：userInfoVo
 * @description：用户登录状态信息实体类
 * @createTime：2022/5/10 09:03
 * @author：kate_chen
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "userInfoVo",description = "用户登录状态信息实体类")
public class userInfoVo {
    @Schema(name = "name",description = "用户名称")
    private String name;
    @Schema(name = "nickName",description = "用户昵称")
    private String nickName;
    @Schema(name = "headImg",description = "头像")
    private String headImg;
    @Schema(name = "mobile",description = "手机号")
    private String mobile;
    @Schema(name = "userType",description = "1：类型1，2：类型2")
    private Integer userType;
    @Schema(name = "token",description = "JWT访问令牌")
    private String token;
}
