package com.achal.todo_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //Cross-Site Request Forgery (CSRF)
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/api/auth/**").permitAll(); //this should not require login as all public
            auth.anyRequest().authenticated(); //else authenticate except that
        });
        http.httpBasic(httpBasic -> httpBasic.disable()); //disable HTTP basic authentication

        http.formLogin(formLogin -> formLogin.disable()); // disable login form
        return http.build();
    }
}
