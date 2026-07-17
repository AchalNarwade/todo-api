package com.achal.todo_api.config;

import com.achal.todo_api.security.JwtAuthenticationFilter;
import com.achal.todo_api.security.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    //dependency injection
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //Cross-Site Request Forgery (CSRF)
        http.csrf(csrf -> csrf.disable()); //disable csrf

        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); //disable session

        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/api/auth/**").permitAll(); //this should not require login as all public
            auth.anyRequest().authenticated(); //else authenticate except that
        });
        http.httpBasic(httpBasic -> httpBasic.disable()); //disable HTTP basic authentication

        http.formLogin(formLogin -> formLogin.disable()); // disable login form

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); //jwt filter added

        return http.build();
    }
}
