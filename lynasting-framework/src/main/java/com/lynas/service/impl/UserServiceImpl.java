package com.lynas.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lynas.domain.ResponseResult;
import com.lynas.domain.vo.UserInfoVo;
import com.lynas.mapper.UserMapper;
import com.lynas.domain.entity.User;
import com.lynas.service.UserService;
import com.lynas.utils.BeanCopyUtils;
import com.lynas.utils.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author LynasTing
 * @since 2024-03-31 15:54:32
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

  @Override
  public ResponseResult getUserInfo() {
    // 拿到id
    Long userId = SecurityUtils.getUserId();
    // 查询出用户信息
    User user = getById(userId);
    // vo封装
    UserInfoVo userInfoVo = BeanCopyUtils.beanCopy(user, UserInfoVo.class);
    // 返回
    return ResponseResult.okResult(userInfoVo);
  }

  @Override
  public ResponseResult putUserInfo(User user) {
    updateById(user);
    return ResponseResult.okResult();
  }
}
