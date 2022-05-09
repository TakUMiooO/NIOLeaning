package com.kate.demo.infrastructure.JWT;

import io.jsonwebtoken.*;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;
import java.util.function.BiConsumer;

/**
 * @className：JWTTests
 * @description：TODO
 * @createTime：2022/5/9 13:50
 * @author：kate_chen
 */
@Slf4j
public class JWTTests {


    private static long tokenExpiration = 1000 * 60;
    private static String tokenSignKey = "supcon123";


    @Test
    public void testCreatedToken() {
        //打开构造器
        val jwtBuilder = Jwts.builder();

        //定义JWT三个部分
        String jwtToken = jwtBuilder
                //JWT头header
                //header包含两个属性
                //Algorithm:HS256表示信息加密所需要的算法
                //typ表示令牌的类型
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                //有效载荷payload
                //payload：自定义信息部分
                .claim("nickname", "peter_chen")
                .claim("attributes", "husband")
                .claim("role", "boy")
                //payload:定义默认信息
                .setSubject("supcon-user")//令牌主题
                .setIssuer("supcon-qianfazhe")//签发者
                .setAudience("accpeter")//接受方
                .setIssuedAt(new Date())//签发时间
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))//过期时间
                .setNotBefore(new Date(System.currentTimeMillis() + 1000 * 20))//生效时间，在某时间之前，JWT不可用//20秒后生效
                .setId(UUID.randomUUID().toString())//表示token的唯一标识，防止被窃取
                //签名哈希verify signature
                .signWith(SignatureAlgorithm.HS256, tokenSignKey)
                //组装3个部分
                .compact();
        log.info(jwtToken);
        System.out.println(jwtToken);
    }

    @Test
    public void decodeJWT() {

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuaWNrbmFtZSI6InBldGVyX2NoZW4iLCJhdHRyaWJ1dGVzIjoiaHVzYmFuZCIsInJvbGUiOiJib3kiLCJzdWIiOiJzdXBjb24tdXNlciIsImlzcyI6InN1cGNvbi1xaWFuZmF6aGUiLCJhdWQiOiJhY2NwZXRlciIsImlhdCI6MTY1MjA4NDI0MywiZXhwIjoxNjUyMDg0MzAzLCJuYmYiOjE2NTIwODQyNjMsImp0aSI6ImZjODg1YWYyLWY3ZjctNDljNy1iZmY1LTkyZWQ0MTczNjk3NyJ9.QxMlWDCUjtUVICpFtEa5qpCE8bTLsQ_H1VhRZNKw3S0";

        val jwtParser = Jwts.parser();

        log.info("start parser...");
        //parseClaimsJws会报错，是因为生成的token过期了
        Jws<Claims> claimsJws = jwtParser.setSigningKey(tokenSignKey).parseClaimsJws(token);
        val body = claimsJws.getBody();
        String role = (String) body.get("role");
        val id = body.getId();
        System.out.println(id);
        System.out.println(role);


    }
}
