package com.github.churchtao.wpaper.intercept;

import com.github.churchtao.wpaper.context.DataContext;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author taojiacheng
 * @create 2018-05-29 15:12
 **/
public class HttpRequestListener extends HandlerInterceptorAdapter {

    /**
     * 前置检查
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 获取用户token
        DataContext.init(request,response);
        return true;
    }

    // controller处理完成
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        DataContext.destroy();
        super.afterCompletion(request, response, handler, ex);
    }
}
