package com.itheima.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * ClassName: LoginCheckInterceptor
 * Package: com.itheima.interceptor
 * Description:
 *
 * @Author Piggie
 * @Create 29/01/2024 9:17 pm
 * @Version 1.0
 */
@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    //  executed before controller method true -> proceed, false -> intercept
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("pre-handle!!!");
        String token = request.getHeader("token");
        if (!StringUtils.hasLength(token)) {
            log.info("token does not exist");
            Result not_login = Result.error("NOT_LOGIN");
            String res = JSONObject.toJSONString(not_login);
            response.getWriter().write(res);
            return false;
        }
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("token invalid!!!");
            e.printStackTrace();
            Result not_login = Result.error("NOT_LOGIN");
            String res = JSONObject.toJSONString(not_login);
            response.getWriter().write(res);
            return false;
        }
        log.info("token good");
        return true;
    }

    @Override
    //  executed after controller method true -> proceed, false -> intercept
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("post-handle!!!");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    //  executed after view has been rendered
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("after completion !!!");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
