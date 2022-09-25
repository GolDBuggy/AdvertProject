package com.java.advertproject.Configuration;

import com.java.advertproject.Security.MemberDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final MemberDetailService memberDetailService;
    private AuthenticationManager authenticationManager;

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception{
        AuthenticationManagerBuilder builder=https.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(memberDetailService);
        authenticationManager= builder.build();
        https.csrf().disable();
        https.authorizeRequests().antMatchers("/advert/all").permitAll().and().
                authorizeRequests().antMatchers("/advert/create","/advert/update","/advert/my").
                hasRole("USER").
                and().formLogin().permitAll().and().logout().permitAll().
                and().authenticationManager(authenticationManager).httpBasic();

        return https.build();
    }





}
