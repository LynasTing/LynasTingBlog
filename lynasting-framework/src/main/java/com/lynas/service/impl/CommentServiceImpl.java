package com.lynas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lynas.constants.SystemConst;
import com.lynas.domain.R;
import com.lynas.domain.entity.Comment;
import com.lynas.domain.vo.CommentVo;
import com.lynas.domain.vo.PageVo;
import com.lynas.enums.AppHttpCodeEnum;
import com.lynas.excepion.SystemException;
import com.lynas.mapper.CommentMapper;
import com.lynas.service.CommentService;
import com.lynas.service.UserService;
import com.lynas.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author LynasTing
 * @since 2024-03-31 14:57:56
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

  @Autowired
  private UserService userService;

  @Override
  public R commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
    // 查询对应文章
    LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();

    // 只有文章评论才查articleId
    queryWrapper.eq(SystemConst.ARTICLE_COMMENT.equals(commentType), Comment::getArticleId, articleId);

    // 查询文章/友链评论
    queryWrapper.eq(Comment::getType, commentType);

    // 查询文章的根评论
    LambdaQueryWrapper<Comment> list = queryWrapper.eq(Comment::getRootId, SystemConst.COMMENT_LIST_ROOT);

    // 分页
    Page<Comment> page = new Page(pageNum, pageSize);
    page(page, queryWrapper);

    // VO字段过滤
    List<CommentVo> arr = toCommentVoList(page.getRecords());

    // 查询对应的回复评论
    for (CommentVo item : arr) {
      List<CommentVo> children = getChildren(item.getId());
      item.setChildren(children);
    }

    // 返回数据是分页类型的，使用PageVo
    return R.okResult(new PageVo(arr, page.getTotal()));
  }



  /**
   * 根据根评论id查回复评论的集合
   * @param id 根评论id
   */
  private List<CommentVo> getChildren(Long id) {
    LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Comment::getRootId, id);
    // 排序
    queryWrapper.orderByAsc(Comment::getCreateTime);
    // 把查询信息转化成list
    List<Comment> comments = list(queryWrapper);

    List<CommentVo> commentVoList = toCommentVoList(comments);
    return commentVoList;
  }


  /**
   * 数据库内容转CommentVo
   */
  private List<CommentVo> toCommentVoList(List<Comment> arr) {
    List<CommentVo> commentVos = BeanCopyUtils.beanListCopy(arr, CommentVo.class);
    for (CommentVo item : commentVos) {
      // 通过createBy查询到用户的昵称，需要借助userService
      String nickname = userService.getById(item.getCreateBy()).getNickname();
      item.setUsername(nickname);

      // 通过toCommentUserId查询到toCommentName
      if(item.getToCommentUserId() != -1) {
        String toCommentName = userService.getById(item.getToCommentUserId()).getNickname();
        item.setToCommentUserName(toCommentName);
      }
    }
    return commentVos;
  }

  /**
   * 新增或回复评论
   *
   * @param comment
   */
  @Override
  public R commentReply(Comment comment) {
    if(!StringUtils.hasText(comment.getContent())) {
      throw new SystemException(AppHttpCodeEnum.CONTENT_IS_NULL);
    }
    // 这个save方法是mybatis-plus内置的
    save(comment);
    return R.okResult();
  }
}
