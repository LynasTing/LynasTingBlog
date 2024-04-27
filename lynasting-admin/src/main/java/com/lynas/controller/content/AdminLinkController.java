package com.lynas.controller.content;

import com.lynas.domain.R;
import com.lynas.domain.dto.content.CategoryPageDto;
import com.lynas.domain.dto.content.LinkEditDto;
import com.lynas.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
  /**
   * 新增友链
   */
  @PostMapping("/add")
  public R addLink(@RequestBody LinkEditDto args) {
    return linkService.addLink(args);
  }
}
