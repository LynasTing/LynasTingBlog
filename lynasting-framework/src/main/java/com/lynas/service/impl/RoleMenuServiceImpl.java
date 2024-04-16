package com.lynas.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lynas.mapper.RoleMenuMapper;
import com.lynas.domain.entity.RoleMenu;
import com.lynas.service.RoleMenuService;
import org.springframework.stereotype.Service;

/**
 * 角色和菜单关联表(RoleMenu)表服务实现类
 *
 * @author LynasTing
 * @since 2024-04-16 20:15:03
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}
