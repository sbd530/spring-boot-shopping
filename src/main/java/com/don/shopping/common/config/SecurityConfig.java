package com.don.shopping.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

/**
 * 스프링 시큐리티 설정 클래스
 * 접근 권한에 대한 설정과 암호화 빈을 등록합니다.
 * 작성자 : 윤병돈
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 요청 권한 설정 정보
     *
     * .authorizeRequests() 이후 작성된 코드 순으로 차례대로 인터셉터를 거칩니다.
     * hasAuthority(권한) : 해당 권한이 있으면 접근이 가능하고, 없으면 로그인 페이지로 이동합니다.
     * hasAnyAuthority(권한1,권한2) : 권한이 하나라도 있으면 위와 같습니다.
     * authenticated() : 로그인하여 인증을 받은 유저라면, 권한에 관계 없이 접근이 허용하지만,
     *                   받지 않은 유저는 로그인 페이지로 이동합니다.
     * anonymous() : 권한이 없는 익명의 유저만 허용합니다. 로그인한 유저는 접근이 불가능합니다.
     * rememberMe() : 쿠키로 자동 로그인을 한 유저의 접근을 허용합니다.
     * permitAll() : 로그인이나 권한에 관계없이 모두에게 접근을 허용합니다.
     * denyAll() : 누구라도 접근을 할 수가 없습니다.
     *
     * 작성자 : 윤병돈
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                    .addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy","script-src 'self'"))
                    .frameOptions().disable()
                    .disable()
                .authorizeRequests()
                    // 대시보드 -> ADMIN 권한
                    //.antMatchers("/dashboard/**").hasAuthority("ADMIN")
                    // 마이페이지
                    .antMatchers("/mypage/**").hasAnyAuthority("USER","ADMIN")
                    // 장바구니
                    .antMatchers("/carts/**").authenticated()
                    // 상기 외의 어떤 요청도 접근을 허용합니다.
                    .anyRequest().permitAll()
                .and()
                    .formLogin()
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .failureUrl("/auth/login/error")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(new LoginSuccessHandler())
                .and()
                    .logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/home")
                        .invalidateHttpSession(true)
                .and()
                    .csrf().disable();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }



}
