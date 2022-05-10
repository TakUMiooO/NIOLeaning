package com.kate.demo.Utils.jwt;

import io.jsonwebtoken.*;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.Getter;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @className：jwtUtils
 * @description：TODO
 * @createTime：2022/5/9 16:37
 * @author：kate_chen
 */
@Slf4j
@Service
public class jwtUtils {

    @Getter
    private static long tokenExpiration = 24 * 1000 * 60;
    @Getter
    private static String tokenSignKey = "supcon123456";

    /*
     * @name: getKeyInstance
     * @description: 加密密钥过程
     * @return: Key
     * @date: 2022/5/9 16:42
     * @auther: Asuma
     */
    private static Key getKeyInstance() {
        return new SecretKeySpec(DatatypeConverter.parseBase64Binary(tokenSignKey), SignatureAlgorithm.HS512.getJcaName());
    }
    public static String createToken(Long userID, String userName) {
        val jwtBuilder = Jwts.builder();
        String token = jwtBuilder
                .setSubject("supcon-User")//设置token主题
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .setIssuer("R&D")
                .claim("userID", userID)
                .claim("userName", userName)
                .signWith(SignatureAlgorithm.HS512, getKeyInstance())
                .compressWith(CompressionCodecs.GZIP)//compressWith() 压缩方法。当载荷过长时可对其进行压缩。可采用jjwt实现的两种压缩方法CompressionCodecs.GZIP和CompressionCodecs.DEFLATE
                .compact();
        return token;
    }
    public static boolean checkToken(String token) {
        if (StringUtils.isEmpty(token)) {
            log.info("token为空");
            return false;
        }
        try {
            Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(token);
            log.info("成功提取token");
            return true;
        } catch (Exception e) {
            log.info("token异常");
            return false;
        }
    }

    public static Long getUserId(String token) throws Exception {
        return (Long) getClaims(token).get("userID");
    }

    public static String getUserName(String token) throws Exception {
        return (String) getClaims(token).get("userName");
    }

    public static boolean removeToken(String token) {
        return false;
    }

    private static Claims getClaims(String token) throws Exception {
        if (StringUtils.isEmpty(token)) {
            log.info("token为空");
            throw new Exception("token为空");
        }
        try {
            val claimsJws = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(token);
            return claimsJws.getBody();
        } catch (Exception e) {
            log.info("登录异常");
            throw new Exception("登录异常");
        }
    }

}
