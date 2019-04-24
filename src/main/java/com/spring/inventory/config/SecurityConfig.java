package com.spring.inventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        return HeaderHttpSessionIdResolver.xAuthToken();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public DeviceResolver deviceResolver() {
//        return new LiteDeviceResolver();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling()
                    .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpStatus.UNAUTHORIZED.value()))
                .and()
                .formLogin()
                    .permitAll()
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .loginProcessingUrl("/login")
                    .successHandler(getAuthenticationSuccessHandler())
                    .failureHandler(getAuthenticationFailureHandler())
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessHandler(getLogoutSuccessHandler())
                .and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/users/create").permitAll()
                    .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    private AuthenticationSuccessHandler getAuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
//            if (deviceResolver().resolveDevice(request).isMobile()) {
//                request.getSession().setMaxInactiveInterval(Integer.MAX_VALUE);
//            } else {
//                request.getSession().setMaxInactiveInterval(60 * 60 * 24);
//            }
            request.getSession().setMaxInactiveInterval(60 * 60 * 24);
            response.setStatus(HttpStatus.OK.value());
        };
    }

    private AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return (request, response, exception) -> response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid Username / Password");
    }

    private LogoutSuccessHandler getLogoutSuccessHandler() {
        return (request, response, authentication) -> response.setStatus(HttpStatus.NO_CONTENT.value());
    }

}
