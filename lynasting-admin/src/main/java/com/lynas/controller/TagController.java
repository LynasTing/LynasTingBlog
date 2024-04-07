package com.lynas.controller;

import com.lynas.domain.R;
import com.lynas.domain.dto.TagQueryDto;
import com.lynas.domain.entity.Tag;
import com.lynas.domain.vo.PageVo;
import com.lynas.domain.vo.TagVo;
import com.lynas.service.TagService;
import com.lynas.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/tag")
public class TagController {
  @Autowired
  private TagService tagService;

  @GetMapping("/list")
  public R<PageVo> getAll(Integer pageNum, Integer pageSize, TagQueryDto arg) {
    return tagService.getAll(pageNum, pageSize, arg);
  }

  @PostMapping("/add")
  public R addTag(@RequestBody TagQueryDto arg) {
    Tag tagArg = BeanCopyUtils.beanCopy(arg, Tag.class);
    return tagService.addTag(tagArg);
  }

  @DeleteMapping("/{id}")
  public R delTag(@PathVariable("id") Long id) {
    return tagService.delTag(id);
  }

  @GetMapping("/{id}")
  public R<TagVo> echoTag(@PathVariable("id") Long id) {
    return tagService.echoTag(id);
  }

  @PutMapping("/edit")
  public R editTag(@RequestBody TagVo arg) {
    return tagService.editTag(arg);
  }

  /**
   * 查询所有标签(字典形式)
   */
  @GetMapping("/listAllTag")
  public R<TagVo> getAllTag() {
    return tagService.getAllTag();
  }
}
