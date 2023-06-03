/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.health.contracts.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author Bobby
 */
@Configuration
public class CustomFilterConfigurer implements WebMvcConfigurer  {
    
    @Autowired
    private SecurityFilter myFilter;

    @Bean
    public FilterRegistrationBean myFilterRegistrationBean() {
        FilterRegistrationBean regBean = new FilterRegistrationBean();
        regBean.setFilter(myFilter);
        regBean.setOrder(1);

        return regBean;
    }
    
}
