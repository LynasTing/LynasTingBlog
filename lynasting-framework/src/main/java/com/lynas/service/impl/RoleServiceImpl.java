package com.lynas.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lynas.mapper.RoleMapper;
import com.lynas.domain.entity.Role;
import com.lynas.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author LynasTing
 * @since 2024-04-06 14:18:04
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

  @Override
  public List<String> selectRolesById(Long id) {
    return getBaseMapper().selectRolesById(id);
  }
}
