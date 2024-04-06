package com.lynas.service;

import com.lynas.domain.R;
import com.lynas.domain.entity.User;

public interface AdminLoginService {
  R login(User user);

  R logout();
}
