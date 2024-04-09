package com.lynas.service.impl;

import com.lynas.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ps")
public class PermissionService {

  /**
   * 判断当前用户是否有操作权限
   * @param per 要判断的权限
   */
  public boolean hasPermission(String per) {
    if(SecurityUtils.isAdmin()) {
      return true;
    }
    List<String> permissions = SecurityUtils.getLoginUser().getPermissions();
    return permissions.contains(per);
  }
}
