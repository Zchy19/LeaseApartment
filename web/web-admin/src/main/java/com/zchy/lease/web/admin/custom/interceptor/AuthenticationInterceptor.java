package com.zchy.lease.web.admin.custom.interceptor;

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
 * @package: com.zchy.lease.web.admin.custom.interceptor
 * @className: HandlerInterceptor
 * @author: ZCH
 * @description:
 * @date: 8/7/2024 3:11 PM
 * @version: 1.0
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("access-token");
        Claims claims = JwtUtil.parseToken(token);
        Long id = claims.get("userId", Long.class);
        LoginUserHolder.setLoginUser(new LoginUser(id, claims.get("username", String.class)));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginUserHolder.removeLoginUser();
    }
}
