package com.don.shopping.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                    .disable()
                .authorizeRequests()
                    //.antMatchers("/dashboard/**").hasRole("ADMIN")
                    .antMatchers("/**")
                        .permitAll()
                .and()
                    .formLogin()
                        .loginPage("/login")
                        //.loginProcessingUrl("/login")
                        .failureUrl("/login/error")
                        //.usernameParameter("email")
                        //.passwordParameter("password")
                        .successHandler(new LoginSuccessHandler())
                        .permitAll()
                .and()
                    .logout()
                        .logoutUrl("/users/logout")
                        .logoutSuccessUrl("/home")
                        .invalidateHttpSession(true)
                .and().csrf().disable();
    }



    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


}
