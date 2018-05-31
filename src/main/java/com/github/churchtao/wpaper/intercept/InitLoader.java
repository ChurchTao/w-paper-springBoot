package com.github.churchtao.wpaper.intercept;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author taojiacheng
 * @create 2018-05-29 15:18
 **/
@Configuration
public class InitLoader implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpRequestListener());
    }
}
