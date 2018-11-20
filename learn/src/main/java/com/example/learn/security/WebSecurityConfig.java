package com.example.learn.security;

import com.example.learn.social.CustomAuthenticationFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public final static String SESSION_KEY="username";

    @Autowired
    CustomAuthenticationFailureHandler customAuthenticationFailureHandler;


    @Override
    protected void configure (HttpSecurity http)  throws Exception{
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("login.html", "/users/add").permitAll()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .defaultSuccessUrl("/user/")
                .failureHandler(customAuthenticationFailureHandler)
                .and()
                .logout().logoutSuccessUrl("/user/login").permitAll();
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
            HttpSession session = request.getSession();

            //判断是否已有该用户登录的session
            if (session.getAttribute(SESSION_KEY) != null) {
                return true;
            }

            //跳转到登录页
            String url = "/login";
            response.sendRedirect(url);
            return false;
        }
    }

}
