package com.lynas.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lynas.domain.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色和菜单关联表(RoleMenu)表数据库访问层
 *
 * @author LynasTing
 * @since 2024-04-16 20:15:01
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

}
