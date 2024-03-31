package com.lynas.controller;

import com.lynas.domain.ResponseResult;
import com.lynas.domain.entity.User;
import com.lynas.enums.AppHttpCodeEnum;
import com.lynas.excepion.SystemException;
import com.lynas.service.BlogLoginService;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BlogLoginController {
  @Autowired
  private BlogLoginService blogLoginService;

  @PostMapping("/login")
  public ResponseResult login(@RequestBody User user) {
    if(!StringUtils.hasText(user.getUserName())) {
      throw  new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
    }
    return blogLoginService.login(user);
  }

  /**
   * 退出
   */
  @PostMapping("/logout")
  public ResponseResult logout() {
    return blogLoginService.logout();
  }
}
