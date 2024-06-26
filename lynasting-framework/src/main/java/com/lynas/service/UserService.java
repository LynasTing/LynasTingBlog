package com.lynas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lynas.domain.R;
import com.lynas.domain.dto.auth.UserAddDto;
import com.lynas.domain.dto.auth.UserEditDto;
import com.lynas.domain.dto.auth.UserPageDto;
import com.lynas.domain.entity.User;

/**
 * 用户表(User)表服务接口
 *
 * @author LynasTing
 * @since 2024-03-31 15:54:31
 */
public interface UserService extends IService<User> {

  R getUserInfo();

  R putUserInfo(User user);

  R register(User user);

  R pageUser(UserPageDto arg);

  R addUser(UserAddDto arg);

  R delUser(Long id);

  R echoUser(Long id);

  R editUser(UserEditDto arg);
}
