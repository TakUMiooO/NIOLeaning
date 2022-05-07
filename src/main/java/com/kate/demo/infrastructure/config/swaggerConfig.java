package com.kate.demo.infrastructure.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;


/**
 * @className：swaggerConfig
 * @description：
 * @createTime：2022/4/25 10:58
 * @author：kate_chen
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Kate Swagger3",
                version = "Kate 1.0",
                description = "KateSwagger3使用演示",
                contact = @Contact(name = "KateContact")
        ),
        security = @SecurityRequirement(name = "Kate JWT"),
        externalDocs = @ExternalDocumentation(description = "Kate 参考文档",
                url = "https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations"
        )
)
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "KateJWT", scheme = "bearer", in = SecuritySchemeIn.HEADER)
public class swaggerConfig {
}
