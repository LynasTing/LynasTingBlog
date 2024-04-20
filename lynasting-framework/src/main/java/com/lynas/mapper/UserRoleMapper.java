package com.lynas.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lynas.domain.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户和角色关联表(UserRole)表数据库访问层
 *
 * @author LynasTing
 * @since 2024-04-20 18:05:57
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

}
