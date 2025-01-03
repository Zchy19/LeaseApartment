package com.zchy.lease.common.utils;

import com.zchy.lease.common.exception.LeaseException;
import com.zchy.lease.common.result.ResultCodeEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * @projectName: lease
 * @package: com.zchy.lease.common.utils
 * @className: JwtUtil
 * @author: ZCH
 * @description: Jwt工具类
 * @date: 8/6/2024 8:11 PM
 * @version: 1.0
 */
public class JwtUtil {
    private static long tokenExpiration= 3600000*24*7*365L;
    private static SecretKey tokenSignKey = Keys.hmacShaKeyFor("Wf36Gf5E2tksYdpCM0uHfKfj0WEn6TDF".getBytes());

    public static String createToken(Long userId, String username) {
        String token = Jwts.builder()
                .setSubject("USER_INFO")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("userId", userId)
                .claim("username", username)
                .signWith(tokenSignKey)
                .compact();
        return token;
    }

    public static Claims parseToken(String token) {
        if (token == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_LOGIN_AUTH);
        }
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(tokenSignKey).build().parseClaimsJws(token);
            return claimsJws.getBody();
        } catch (ExpiredJwtException e) {
            throw new LeaseException(ResultCodeEnum.TOKEN_EXPIRED);
        } catch (JwtException e) {
            throw new LeaseException(ResultCodeEnum.TOKEN_INVALID);
        }
    }

    public static void main(String[] args) {
        System.out.println(JwtUtil.createToken(8L, "13405122389"));
    }
}
