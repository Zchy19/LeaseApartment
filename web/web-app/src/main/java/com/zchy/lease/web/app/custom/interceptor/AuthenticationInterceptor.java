package com.zchy.lease.web.app.custom.interceptor;

import com.zchy.lease.common.login.LoginUser;
import com.zchy.lease.common.login.LoginUserHolder;
import com.zchy.lease.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @projectName: lease
 * @package: com.zchy.lease.web.app.custom.interceptor
 * @className: AuthenticationInterceptor
 * @author: ZCH
 * @description:
 * @date: 8/8/2024 10:22 PM
 * @version: 1.0
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("access-token");
        Claims claims = JwtUtil.parseToken(token);
        Long userId = claims.get("userId", Long.class);
        String username = claims.get("username", String.class);
        LoginUserHolder.setLoginUser(new LoginUser(userId, username));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginUserHolder.removeLoginUser();
    }
}
