package com.husu.common.interceptor;

import com.husu.common.log.LoggerContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ricardo.Y.Hu
 * @date 2023/6/25 10:51
 */
public class LoggerContextInterceptor implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoggerContext.clear();
    }
}

