package com.lynas.service;

import com.lynas.domain.R;
import com.lynas.domain.entity.User;

public interface BlogLoginService {
  R login(User user);

  R logout();
}
