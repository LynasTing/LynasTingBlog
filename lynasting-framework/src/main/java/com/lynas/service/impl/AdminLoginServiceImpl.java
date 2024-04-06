package com.lynas.service.impl;

import com.lynas.constants.SystemConst;
import com.lynas.domain.R;
import com.lynas.domain.entity.LoginUser;
import com.lynas.domain.entity.User;
import com.lynas.service.AdminLoginService;
import com.lynas.utils.JwtUtil;
import com.lynas.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class AdminLoginServiceImpl implements AdminLoginService {

  @Autowired
  private AuthenticationManager authManager;

  @Autowired
  private RedisCache redisCache;

  @Override
  public R login(User user) {
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
    Authentication authenticate = authManager.authenticate(token);

    // 检查传入信息是否正确
    if(Objects.isNull(authenticate)) {
      throw new RuntimeException("用户名或密码错误");
    }

    // 获取用户userId, 生成token
    LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
    String userId = loginUser.getUser().getId().toString();
    String jWt = JwtUtil.createJWT(userId);

    // 将用户信息存入redis
    redisCache.setCacheObject(SystemConst.ADMIN_REDIS_TOKEN + userId, loginUser);

    // 封装token
    Map<String, String> loginRes = new HashMap<>();
    loginRes.put("token", jWt);

    // 返回token
    return R.okResult(loginRes);
  }

  @Override
  public R logout() {
    Authentication token = SecurityContextHolder.getContext().getAuthentication();
    LoginUser loginUser = (LoginUser) token.getPrincipal();
    Long id = loginUser.getUser().getId();
    redisCache.deleteObject(SystemConst.ADMIN_REDIS_TOKEN + id);
    return R.okResult();
  }
}
