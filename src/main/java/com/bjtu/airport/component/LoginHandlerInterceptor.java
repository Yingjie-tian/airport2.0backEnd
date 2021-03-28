package com.bjtu.airport.component;


import com.bjtu.airport.annotation.PassToken;
import com.bjtu.airport.contant.ResultCode;
import com.bjtu.airport.exception.CustomException;
import com.bjtu.airport.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author: LiuYi
 * @date: 2019/4/18 16:32
 */
@Slf4j
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
//        检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        Claims claims = null;
        try {
            // 执行认证
            if (token == null) {
                throw new CustomException(ResultCode.UNAUTHENTICATED);
            }
            // 验证 token
            claims = JwtUtils.parseJWT(token);
            if (claims == null) {
                throw new CustomException(ResultCode.UNAUTHENTICATED);
            }
        } catch (Exception e) {
//            log.error(e.getMessage());
            throw e;
        }
        request.setAttribute("tu", claims.get("userId"));
        request.setAttribute("tr", claims.get("role"));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
