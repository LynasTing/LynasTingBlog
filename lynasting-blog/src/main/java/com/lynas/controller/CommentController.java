package com.lynas.controller;


import com.lynas.constants.SystemConst;
import com.lynas.domain.R;
import com.lynas.domain.dto.ReplyCommentDto;
import com.lynas.domain.entity.Comment;
import com.lynas.service.CommentService;
import com.lynas.utils.BeanCopyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@Api(tags = "评论", description = "评论相关接口")
public class CommentController {

  @Autowired
  private CommentService commentService;

  @GetMapping("list")
  public R commentList(Long articleId, Integer pageNum, Integer pageSize) {
    return  commentService.commentList(SystemConst.ARTICLE_COMMENT, articleId, pageNum, pageSize);
  }

  @PostMapping("/reply")
  public R commentReply(@RequestBody ReplyCommentDto commentDto) {
    Comment comment = BeanCopyUtils.beanCopy(commentDto, Comment.class);
    return commentService.commentReply(comment);
  }

  /**
   * 友链评论列表
   */
  @GetMapping("/linkCommentList")
  @ApiOperation(value = "友链评论列表", notes = "获取一页友链评论")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "pageNum", value = "页码"),
    @ApiImplicitParam(name = "pageSize", value = "每页查询条数", required = true)
  })
  public R linkCommentList(Integer pageNum, Integer pageSize) {
    return commentService.commentList(SystemConst.LINK_COMMENT, null,  pageNum, pageSize);
  }
}
