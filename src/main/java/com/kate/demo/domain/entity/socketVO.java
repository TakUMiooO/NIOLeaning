package com.kate.demo.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className：socketVO
 * @description：socket实体类
 * @createTime：2022/4/25 10:48
 * @author：kate_chen
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "testSwagger",description = "后端swagger测试")
public class socketVO {
    @Schema(name = "port",description = "socket的端口")
    private Integer port;
    @Schema(name = "workCount",description = "socket的端口")
    private Integer workCount;
    @Schema(name = "allowCustomRequests",description = "socket的端口")
    private Boolean allowCustomRequests;
    @Schema(name = "upgradeTimeout",description = "socket的端口")
    private Integer upgradeTimeout;
    @Schema(name = "pingTimeout",description = "socket的端口")
    private Integer pingTimeout;
    @Schema(name = "pingInterval",description = "socket的端口")
    private Integer pingInterval;
    @Schema(name = "maxFramePayloadLength",description = "socket的端口")
    private Integer maxFramePayloadLength;
    @Schema(name = "maxHttpContentLength",description = "socket的端口")
    private Integer maxHttpContentLength;
}
