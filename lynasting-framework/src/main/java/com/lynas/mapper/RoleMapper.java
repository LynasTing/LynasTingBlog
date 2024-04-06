package com.lynas.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lynas.domain.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author LynasTing
 * @since 2024-04-06 14:21:20
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
  List<String> selectRolesById(Long id);

}
