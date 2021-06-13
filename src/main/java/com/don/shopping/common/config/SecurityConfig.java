package com.don.shopping.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                    .addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy","script-src 'self'"))
                    .frameOptions().disable()
                    .disable()
                .authorizeRequests()
                    .antMatchers("/h2-console/**", "/static/**", "/auth/**")
                        .permitAll()
                    .anyRequest()
                        .authenticated()
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .failureUrl("/login/error")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(new LoginSuccessHandler())
                .and()
                    .csrf()
                    .disable()
                .logout()
                    .logoutUrl("/users/logout")
                    .logoutSuccessUrl("/home")
                .disable();

    }
}
