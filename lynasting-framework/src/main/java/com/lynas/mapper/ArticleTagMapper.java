package com.lynas.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lynas.domain.entity.ArticleTag;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章标签关联表(ArticleTag)表数据库访问层
 *
 * @author LynasTing
 * @since 2024-04-07 15:31:28
 */
@Mapper
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

}
