package com.lynas.handler.security;

import com.alibaba.fastjson.JSON;
import com.beust.ah.A;
import com.lynas.domain.R;
import com.lynas.enums.AppHttpCodeEnum;
import com.lynas.utils.WebUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理器
 * @author LynasTing
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
    e.printStackTrace();

    // 返回的数据类型得是R
    R result = null;
    if(e instanceof BadCredentialsException) {
      result = R.errorResult(AppHttpCodeEnum.LOGIN_ERROR.getCode(), e.getMessage());
    }else if(e instanceof InsufficientAuthenticationException) {
      result = R.errorResult(AppHttpCodeEnum.NEED_LOGIN);
    }else {
      result = R.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(), "认证或授权失败");
    }
    // 响应给前端
    WebUtils.renderString(response, JSON.toJSONString(result));
  }
}
