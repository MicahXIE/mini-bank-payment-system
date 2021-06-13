package com.micah.demo.auth.jwt;

import com.micah.demo.auth.UserContext;
import com.micah.demo.exceptions.InvalidJwtTokenException;
import com.micah.demo.exceptions.RequestHeaderNotFoundException;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JwtInterceptor implements HandlerInterceptor {
    public static final String LOGIN_URI = "/api/v1/user/login";

    private final Logger LOGGER = LoggerFactory.getLogger(JwtInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("login URI is: {}", request.getRequestURI());
        if(LOGIN_URI.equals(request.getRequestURI()))
            return true;

        String authHeader = request.getHeader("Authorization");
        if (authHeader ==null || authHeader.isBlank())
            throw new RequestHeaderNotFoundException("request header token not found exception.");

        Claims claims = JwtTokenProvider.parseToken(authHeader);
        if (claims != null) {
            UserContext.add(claims.getSubject());
            return true;
        } else {
            throw new InvalidJwtTokenException("interceptor parse jwt token exception.");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.remove();
    }
}
