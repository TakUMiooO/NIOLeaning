package com.kate.demo.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className：chatDto
 * @description：TODO
 * @createTime：2022/4/25 13:17
 * @author：kate_chen
 */
@Schema(name = "chatDto",description = "chatDtoDesc")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class chatDto {
    @Schema(name = "clientId",description = "客户端ID唯一")
    private String clientId;

    @Schema(name = "msg",description = "发送的消息")
    private String msg;
}
