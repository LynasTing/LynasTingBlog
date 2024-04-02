package com.lynas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lynas.domain.ResponseResult;
import com.lynas.domain.entity.Comment;

/**
 * 评论表(Comment)表服务接口
 *
 * @author LynasTing
 * @since 2024-03-31 14:57:56
 */
public interface CommentService extends IService<Comment> {

  ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

  ResponseResult commentReply(Comment comment);
}
