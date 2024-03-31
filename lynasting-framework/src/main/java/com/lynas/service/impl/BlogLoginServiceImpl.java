package com.lynas.service.impl;

import com.lynas.domain.ResponseResult;
import com.lynas.domain.entity.LoginUser;
import com.lynas.domain.entity.User;
import com.lynas.domain.vo.BlogUserLoginVo;
import com.lynas.domain.vo.UserInfoVo;
import com.lynas.service.BlogLoginService;
import com.lynas.utils.BeanCopyUtils;
import com.lynas.utils.JwtUtil;
import com.lynas.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private RedisCache redisCache;

  @Override
  public ResponseResult login(User user) {
    System.out.println("user" + user);
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
    Authentication authenticate = authenticationManager.authenticate(authToken);

    // 判断认证是否通过
    if (Objects.isNull(authenticate)) {
      throw new RuntimeException("用户名或密码错误");
    }

    // 获取userId,生成Token
    LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
    String userId = loginUser.getUser().getId().toString();
    String jwt = JwtUtil.createJWT(userId);

    // 把用户信息存入redis
    redisCache.setCacheObject("bloglogin" + userId, loginUser);

    // 拿到userInfo
    UserInfoVo userInfoVo = BeanCopyUtils.beanCopy(loginUser.getUser(), UserInfoVo.class);

    // 将token和userInfo封装成一个对象
    BlogUserLoginVo blogUserLoginVo = new BlogUserLoginVo(jwt, userInfoVo);
    return ResponseResult.okResult(blogUserLoginVo);
  }

  @Override
  public ResponseResult logout() {
    // 获取token
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    // 获取到用户信息
    LoginUser loginUser = (LoginUser) authentication.getPrincipal();

    // 解析出userid
    Long id = loginUser.getUser().getId();

    // 在redis中删除这个用户的信息
    redisCache.deleteObject("bloglogin" + id);

    return ResponseResult.okResult();
  }

}
