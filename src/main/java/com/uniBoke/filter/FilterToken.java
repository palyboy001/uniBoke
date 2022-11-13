package com.uniBoke.filter;

import javax.servlet.*;
import java.io.BufferedReader;
import java.io.IOException;

public class FilterToken implements Filter {

    /**
     * 过滤器初始化
     * */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
    }

    /**
     * 过滤器执行
     * */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("过滤器执行");
        String parameter = request.getParameter("token");
//        new String(reader),
        System.out.println(parameter);
        // 过滤器放行
//        response.getWriter().write("false");
        chain.doFilter(request,response);
    }

    /**
     * 过去器销毁
     * */
    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }
}
