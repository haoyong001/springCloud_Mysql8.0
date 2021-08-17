package com.atguigu.springcloud.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mr. Hao
 * @date 2021-08-16   22:58
 */
public class MyInterCeptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入拦截器preHandle！");
        String contextPath = request.getContextPath();
        System.out.println("contextPath:" + contextPath);
        String method = request.getMethod();
        System.out.println("method:" + method);
        String requestURI = request.getRequestURI();
        System.out.println("requestURI:" + requestURI);
        StringBuffer requestURL = request.getRequestURL();
        System.out.println("requestURL:" + requestURL);
        String s = handler.toString();
        System.out.println("handler:" + s);
        response.sendRedirect(contextPath + "/payment/feign/timeout");
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("进入拦截器postHandle！");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("进入拦截器afterCompletion！");
    }

}
