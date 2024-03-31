package com.lynas.service;

import com.lynas.domain.ResponseResult;
import com.lynas.domain.entity.User;

public interface BlogLoginService {
  ResponseResult login(User user);

  ResponseResult logout();
}
