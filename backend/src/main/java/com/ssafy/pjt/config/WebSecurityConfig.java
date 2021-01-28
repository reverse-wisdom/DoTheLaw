package com.ssafy.pjt.config;

import com.ssafy.pjt.core.security.Role;
import com.ssafy.pjt.exception.JwtAccessDeniedHandler;
import com.ssafy.pjt.exception.JwtAuthenticationEntryPoint;
import com.ssafy.pjt.provider.security.JwtAuthTokenProvider;
import com.ssafy.pjt.security.JWTConfigurer;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthTokenProvider jwtAuthTokenProvider;
    private final JwtAuthenticationEntryPoint authenticationErrorHandler;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
    	httpSecurity
                .csrf().disable()

                .exceptionHandling()
                .authenticationEntryPoint(authenticationErrorHandler)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/api/member/login/**").permitAll() // 로그인
                .antMatchers("/api/member/signup/**").permitAll() // 회원가입
                .antMatchers("/api/member/social/**").permitAll()
                .antMatchers("/api/member/check/**").permitAll()
                
                .antMatchers("/api/rss/**").permitAll() // RSS 파싱 관련
                
                .antMatchers("/swagger-resources/**").permitAll() //swagger
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/webjars/**").permitAll()
                
                .antMatchers("/api/v1/coffees/**").hasAnyAuthority(Role.USER.getCode(), Role.LAWYER.getCode(), Role.ADMIN.getCode()) //test
                .antMatchers("/api/board/**").hasAnyAuthority(Role.USER.getCode(), Role.LAWYER.getCode(), Role.ADMIN.getCode()) //test
                .anyRequest().authenticated()

                .and()
                .apply(securityConfigurerAdapter());
    }

    @Override
    public void configure(WebSecurity web) {
    	web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")

                // allow anonymous resource requests
                .antMatchers(
                        "/"                    
                );
    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(jwtAuthTokenProvider);
    }
}
