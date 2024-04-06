package com.lynas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lynas.domain.entity.Role;

import java.util.List;

/**
 * 角色信息表(Role)表服务接口
 *
 * @author LynasTing
 * @since 2024-04-06 14:18:04
 */
public interface RoleService extends IService<Role> {
  List<String> selectRolesById(Long id);
}
