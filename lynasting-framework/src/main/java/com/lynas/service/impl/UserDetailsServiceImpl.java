package com.lynas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lynas.domain.entity.LoginUser;
import com.lynas.domain.entity.User;
import com.lynas.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserMapper userMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // 根据用户名查询用户信息
    LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
    // 只查一条
    queryWrapper.eq(User::getUserName, username);
    User user = userMapper.selectOne(queryWrapper);
    // 判断是否查到用户，如果没查到抛出异常
    if(Objects.isNull(user)) {
      throw new RuntimeException("未查询到用户信息");
    }
    // 返回用户信息
    // TODO 查询权限信息封装
    return new LoginUser(user);
  }
}
