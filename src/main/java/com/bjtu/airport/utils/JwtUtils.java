package com.bjtu.airport.utils;


import com.bjtu.airport.contant.ResultCode;
import com.bjtu.airport.entity.SysUser;
import com.bjtu.airport.exception.CustomException;
import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @author: LiuYi
 * @date: 2019/4/20 0:22
 */
public class JwtUtils {

    //    public static final String clientId = "098f6bcd4621d373cade4e832627b4f6";
    public static final String base64Secret = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjYLY";
    public static final String issuer = "airport";
    //    24 * 3600 * 1000
    public static final int expiresSecond = 24 * 3600 * 1000;

    /**
     * 解析jwt
     */
    public static Claims parseJWT(String jsonWebToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Secret))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (ExpiredJwtException eje) {
            throw new CustomException(ResultCode.TOKEN_EXPIRED_ERROR);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 构建jwt
     */
    public static String createJWT(SysUser user, String audience) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Secret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
//                .claim("role", user.getRole())
                .claim("userName", user.getUsername())
                .claim("userId", user.getId())
                .setIssuer(issuer)
                .setAudience(audience)
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (expiresSecond >= 0) {
            long expMillis = nowMillis + expiresSecond;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }

        //生成JWT
        return builder.compact();
    }
}