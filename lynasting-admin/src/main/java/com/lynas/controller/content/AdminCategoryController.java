package com.lynas.controller.content;

import com.lynas.domain.R;
import com.lynas.domain.dto.content.CategoryEditDto;
import com.lynas.domain.dto.content.CategoryPageDto;
import com.lynas.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/category")
public class AdminCategoryController {

  @Autowired
  private CategoryService categoryService;

  /**
   * 分类分页
   */
  @GetMapping("/list")
  public R pageCategory (@ModelAttribute CategoryPageDto arg) {
    return categoryService.pageCategory(arg);
  }

  /**
   * 新增分类
   */
  @PostMapping("/add")
  public R addCategory(@RequestBody CategoryEditDto arg) {
    return categoryService.addCategory(arg);
  }
}
