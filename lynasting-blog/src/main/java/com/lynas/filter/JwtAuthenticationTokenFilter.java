package com.lynas.filter;

import com.alibaba.fastjson.JSON;
import com.lynas.domain.ResponseResult;
import com.lynas.domain.entity.LoginUser;
import com.lynas.enums.AppHttpCodeEnum;
import com.lynas.utils.JwtUtil;
import com.lynas.utils.RedisCache;
import com.lynas.utils.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

  private RedisCache redisCache;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    // 获取请求头中的token
    String token = request.getHeader("token");

    // 如果拿不到token，说明接口不需要校验，直接放行
    if(!StringUtils.hasText(token)) {
      filterChain.doFilter(request, response);
      return;
    }
    Claims claims = null;

    // 解析获取userId
    try {
      claims = JwtUtil.parseJWT(token);
    }catch (Exception e) {
      e.printStackTrace();
      // Token已过期或是非法Token，响应给前端，需要重新登录
      // 封装成Response对象，然后转换成Json
      ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
      WebUtils.renderString(response, JSON.toJSONString(result));
      return;
    }

    // 从redis中获取用户信息
    String userId = claims.getSubject();
    LoginUser cacheObject = redisCache.getCacheObject("bloglogin" + userId);
    if(Objects.isNull(cacheObject)) {
      // redis查不到直接当作未登录处理
      ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
      WebUtils.renderString(response, JSON.toJSONString(result));
      return;
    }

    // 存入SecurityContextHolder
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(cacheObject, null, null);
    SecurityContextHolder.getContext().setAuthentication(authToken);

    // 放行
    filterChain.doFilter(request, response);
  }
}
