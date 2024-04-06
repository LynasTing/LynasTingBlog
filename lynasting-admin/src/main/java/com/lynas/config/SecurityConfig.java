package com.lynas.config;

import com.lynas.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Autowired
  private JwtAuthenticationTokenFilter jwtFilter;

  @Autowired
  AuthenticationEntryPoint authenticationEntryPoint;

  @Autowired
  AccessDeniedHandler accessDeniedHandler;

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      // 关闭csrf
      .csrf().disable()
      // 不通过Session获取SecurityContext
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .authorizeRequests()
      // 对于登录接口 允许匿名访问
      .antMatchers("/user/login").anonymous()
      // .antMatchers("/logout").authenticated()
      // .antMatchers("/user/info").authenticated()
      // .antMatchers("/upload").authenticated()
      // 除上面外的所有请求全部不需要认证即可访问
      .anyRequest().authenticated();
    // 配置异常处理器
    http.exceptionHandling()
      .authenticationEntryPoint(authenticationEntryPoint)
      .accessDeniedHandler(accessDeniedHandler);
    http.logout().disable();
    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    // 允许跨域
    http.cors();
  }
}