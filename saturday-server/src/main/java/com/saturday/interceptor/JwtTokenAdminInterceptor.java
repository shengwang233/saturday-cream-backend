package com.saturday.interceptor;


import com.saturday.context.BaseContext;
import com.saturday.properties.JwtProperties;
import com.saturday.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt令牌校验的拦截器
 */
@Component

public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校验jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //Check whether the current request is mapped to a Controller method or some other resource
        if (!(handler instanceof HandlerMethod)) {
            // If it's not a Controller method (e.g., static resources), allow the request return true;
            return true;
        }

        // 1. Get the token from the request header
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        // 2. Validate the token
        try {
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Long empId = Long.valueOf(claims.get("empId").toString());
            BaseContext.setCurrentId(empId);
            // 3. Token is valid, allow the request
            return true;
        } catch (Exception ex) {
            // 4. Invalid token, respond with 401 Unauthorized
            response.setStatus(401);
            return false;
        }
    }
}
