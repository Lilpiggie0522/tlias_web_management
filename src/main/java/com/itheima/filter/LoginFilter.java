package com.itheima.filter;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * ClassName: LoginFilter
 * Package: com.itheima.filter
 * Description:
 *
 * @Author Piggie
 * @Create 29/01/2024 8:07 pm
 * @Version 1.0
 */
@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String url = req.getRequestURL().toString();
        log.info("url is {}", url);
        if (url.contains("login")) {
            log.info("logging in ");
            chain.doFilter(request, response);
            return;
        }
        String token = req.getHeader("token");
        if (!StringUtils.hasLength(token)) {
            log.info("token in header is null, returning user not logged in");
            Result error = Result.error("NOT_LOGIN");
            //  manually transfer result into json format
            //  use alibaba fast json conversion
            String jsonString = JSONObject.toJSONString(error);
            log.info(jsonString);
            response.getWriter().write(jsonString);
            return;
        }
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("token analysis failed, return to login");
            Result error = Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(error);
            response.getWriter().write(jsonString);
            return;
        }
        log.info("token legal, proceed!");
        chain.doFilter(request, response);
    }
}
