package com.lynas.controller;

import com.lynas.domain.R;
import com.lynas.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content/tag")
public class TagController {
  @Autowired
  private TagService tagService;

  @GetMapping("/list")
  public R list() {
    return R.okResult(tagService.list());
  }
}
