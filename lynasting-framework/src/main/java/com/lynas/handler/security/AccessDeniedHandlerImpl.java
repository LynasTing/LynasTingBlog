package com.lynas.handler.security;

import com.alibaba.fastjson.JSON;
import com.lynas.domain.R;
import com.lynas.enums.AppHttpCodeEnum;
import com.lynas.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
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
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
    e.printStackTrace();

    // 返回的数据类型得是R
    R result = R.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH);

    // 响应给前端
    WebUtils.renderString(response, JSON.toJSONString(result));
  }

}
