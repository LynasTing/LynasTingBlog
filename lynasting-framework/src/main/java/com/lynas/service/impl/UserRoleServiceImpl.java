package com.lynas.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lynas.mapper.UserRoleMapper;
import com.lynas.domain.entity.UserRole;
import com.lynas.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户和角色关联表(UserRole)表服务实现类
 *
 * @author LynasTing
 * @since 2024-04-20 18:14:48
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
