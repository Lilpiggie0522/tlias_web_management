package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

/**
 * ClassName: SecondFilter
 * Package: com.itheima.filter
 * Description:
 *
 * @Author Piggie
 * @Create 29/01/2024 7:01 pm
 * @Version 1.0
 */
//@WebFilter(urlPatterns = "/*")
public class SecondFilter implements Filter {
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
        System.out.println("second filter executing now");
        chain.doFilter(request, response);
        System.out.println("second filter execution finished");
    }
}
