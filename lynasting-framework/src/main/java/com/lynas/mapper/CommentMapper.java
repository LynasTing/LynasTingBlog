package com.lynas.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lynas.domain.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评论表(Comment)表数据库访问层
 *
 * @author LynasTing
 * @since 2024-03-31 14:57:54
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
