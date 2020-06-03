package com.lds.supermarket.config;

import com.lds.supermarket.interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * mvc自定义配置
 */
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    //设置访问路径跳转的页面，
    @Bean
           public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
            WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter(){
//                @Override
//                public void addViewControllers(ViewControllerRegistry registry) {
//                    registry.addViewController("/").setViewName("login");
//                    registry.addViewController("/login.html").setViewName("login");
////                        registry.addViewController("/index.html").setViewName("index");
//                }

            //配置拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor())
                        .addPathPatterns("/**")
                        //必须将js、css等静态文件排除，否则会在某些浏览器中无法加载
                        .excludePathPatterns("/pages/login.html","/assets/**","/login/*",
                                "/pages/forgotPassword.html","/user/getVerification","/user/fogetPassWord");
            }
        };
        return  adapter;
    }
}
