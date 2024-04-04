package com.lynas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lynas.domain.R;
import com.lynas.domain.vo.UserInfoVo;
import com.lynas.enums.AppHttpCodeEnum;
import com.lynas.excepion.SystemException;
import com.lynas.mapper.UserMapper;
import com.lynas.domain.entity.User;
import com.lynas.service.UserService;
import com.lynas.utils.BeanCopyUtils;
import com.lynas.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 用户表(User)表服务实现类
 *
 * @author LynasTing
 * @since 2024-03-31 15:54:32
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

  @Override
  public R getUserInfo() {
    // 拿到id
    Long userId = SecurityUtils.getUserId();
    // 查询出用户信息
    User user = getById(userId);
    // vo封装
    UserInfoVo userInfoVo = BeanCopyUtils.beanCopy(user, UserInfoVo.class);
    // 返回
    return R.okResult(userInfoVo);
  }

  @Override
  public R putUserInfo(User user) {
    updateById(user);
    return R.okResult();
  }

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public R register(User user) {
    // 非空判断
    if(!StringUtils.hasText(user.getUsername())) {
      throw new SystemException(AppHttpCodeEnum.USERNAME_IS_NULL);
    }
    if(!StringUtils.hasText(user.getNickname())) {
      throw new SystemException(AppHttpCodeEnum.NICKNAME_IS_NULL);
    }
    if(!StringUtils.hasText(user.getPassword())) {
      throw new SystemException(AppHttpCodeEnum.PASSWORD_IS_NULL);
    }
    if(!StringUtils.hasText(user.getEmail())) {
      throw new SystemException(AppHttpCodeEnum.EMAIL_IS_NULL);
    }
    // 数据是否已存在
    if(usernameExist(user.getUsername())) {
      throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
    }
    // 密码加密
    String encode = passwordEncoder.encode(user.getPassword());
    user.setPassword(encode);
    // 插入
    save(user);
    return R.okResult();
  }

  private boolean usernameExist(String username) {
    LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(User::getUsername, username);
    return count(queryWrapper) > 0;
  }
}
