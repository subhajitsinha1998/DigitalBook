package com.subhajit2251447.digitalbook.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.subhajit2251447.digitalbook.user.model.Role;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and()
        .headers().frameOptions().disable();
        http.csrf().disable().authorizeHttpRequests()
        .requestMatchers("/api/v1/digitalbooks/sign-in/**", 
            "/api/v1/digitalbooks/sign-up/**", "/api/v1/digitalbooks/search**").permitAll()
        .requestMatchers("/api/v1/digitalbooks/author/**").hasAuthority(Role.AUTHOR.toString())
        .requestMatchers("/api/v1/digitalbooks/reader/**").hasAnyAuthority(Role.AUTHOR.toString(), Role.READER.toString())
        .anyRequest().permitAll()                                     
        .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
