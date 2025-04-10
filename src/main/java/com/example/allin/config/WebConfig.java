package com.example.allin.config;

import com.example.allin.filter.LoginFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
@Configuration, @Bean 두 개가 만나야 스프링이 관리하는 빈이 된다
 */
// @Configuration : 이거 빈이야! 스프링 너가 관리해
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // @Bean : 이거 빈이야!
    @Bean
    // FilterRegistration : 이거 필터야! 라고 등록해주기
    public FilterRegistrationBean<LoginFilter> loginFilter () {

        FilterRegistrationBean<LoginFilter> filterRegistrationBean = new FilterRegistrationBean<>();

        filterRegistrationBean.setFilter(new LoginFilter());

        // 몇번째로 실행해줄지 정하기
        filterRegistrationBean.setOrder(1);

        // 모든 URL은 이 필터를 가져가야해라고 해주기
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }

}
