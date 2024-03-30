package com.lynas.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lynas.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表(User)表数据库访问层
 *
 * @author LynasTing
 * @since 2024-03-28 15:34:29
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
