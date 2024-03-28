package com.lynas.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lynas.domain.entity.Link;
import org.apache.ibatis.annotations.Mapper;

/**
 * 友链(Link)表数据库访问层
 *
 * @author LynasTing
 * @since 2024-03-28 10:33:20
 */
@Mapper
public interface LinkMapper extends BaseMapper<Link> {

}
