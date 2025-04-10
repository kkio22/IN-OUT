package com.example.allin.config;

import com.example.allin.filter.FriendFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<FriendFilter> friendFilterRegistration() {
        FilterRegistrationBean<FriendFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new FriendFilter()); // FriendFilter 등록
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}

