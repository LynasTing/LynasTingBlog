package com.lynas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lynas.domain.ResponseResult;
import com.lynas.domain.entity.User;

/**
 * 用户表(User)表服务接口
 *
 * @author LynasTing
 * @since 2024-03-31 15:54:31
 */
public interface UserService extends IService<User> {

  ResponseResult getUserInfo();

  ResponseResult putUserInfo(User user);

  ResponseResult register(User user);
}
