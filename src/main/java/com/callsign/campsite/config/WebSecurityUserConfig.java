package com.callsign.campsite.config;

import com.callsign.campsite.auth.JwtAuthenticationFilter;
import com.callsign.campsite.auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Order(1)
public class WebSecurityUserConfig {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()      //basic auth 사용안함
                .csrf().disable()           //csrf 보안 사용안함
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)     //JWT를 사용하기에 세션 사용 안함
                .and()
                .authorizeRequests()
                .antMatchers("/members/login").permitAll()          //로그인 시에는 모든 요청 허용
                .antMatchers("/members/test").hasRole("USER")       //해당 요청시에는 user권한이 있어야 함
                .anyRequest().authenticated()                                   //그 외에는 인증이 필요
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);    //JWT필터를 UsernamePasswordAuthenticationFilter 전에 실행
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
