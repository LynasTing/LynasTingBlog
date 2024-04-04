package com.lynas.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lynas.domain.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

/**
 * 标签(Tag)表数据库访问层
 *
 * @author LynasTing
 * @since 2024-04-04 16:39:26
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

}
