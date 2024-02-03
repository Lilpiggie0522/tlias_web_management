package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

/**
 * ClassName: DemoFilter
 * Package: com.itheima.filter
 * Description:
 *
 * @Author Piggie
 * @Create 29/01/2024 6:09 pm
 * @Version 1.0
 */
//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {
    @Override
    //  initialization, only called once
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init called");
        Filter.super.init(filterConfig);
    }

    @Override
    //  free resources, only called once
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("filter1 executing");
        //  proceeds requests
        chain.doFilter(request, response);
        System.out.println("filter1 execution finished");
    }

    //  called multiple times
    @Override
    public void destroy() {
        System.out.println("destroy called");
        Filter.super.destroy();
    }

}
