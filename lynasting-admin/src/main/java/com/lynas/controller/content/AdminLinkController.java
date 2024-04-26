package com.lynas.controller.content;

import com.lynas.domain.R;
import com.lynas.domain.dto.content.CategoryPageDto;
import com.lynas.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/content/link")
@RestController
public class AdminLinkController {
  @Autowired
  private LinkService linkService;

  /**
   * 分页查询友链
   */
  @GetMapping("list")
  public R pageLink(@ModelAttribute CategoryPageDto args) {
    return linkService.pageLink(args);
  }
}
