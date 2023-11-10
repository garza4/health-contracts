///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.health.contracts.security;
//
//import com.health.contracts.service.UserImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
///**
// *
// * @author Bobby
// */
//@Configuration
//@EnableWebSecurity
//public class WebSecurity {
//    public UserImpl userImpl;
//    @Autowired
//    public WebSecurity(UserImpl userImpl){
//        this.userImpl=userImpl;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//            http
//                    .authorizeHttpRequests((requests) -> requests
//                            .requestMatchers("/", "/home").permitAll()
//                            .anyRequest().authenticated()
//                    )
//
//                    .logout((logout) -> logout.permitAll());
//
//            return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//            UserDetails user =
//                     User.withDefaultPasswordEncoder()
//                            .username("user")
//                            .password("password")
//                            .roles("USER")
//                            .build();
//
//            return new InMemoryUserDetailsManager(user);
//    }
//
//}
