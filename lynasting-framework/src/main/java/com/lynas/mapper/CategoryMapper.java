package com.lynas.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lynas.domain.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * 分类表(Category)表数据库访问层
 *
 * @author LynasTing
 * @since 2024-03-25 10:33:42
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
