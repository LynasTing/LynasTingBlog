package com.lynas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lynas.domain.R;
import com.lynas.domain.dto.content.CategoryEditDto;
import com.lynas.domain.dto.content.CategoryPageDto;
import com.lynas.domain.entity.Category;
import com.lynas.domain.vo.CategoryVo;

import java.util.List;

/**
 * 分类表(Category)表服务接口
 *
 * @author LynasTing
 * @since 2024-03-25 10:43:59
 */
public interface CategoryService extends IService<Category> {

  R getList();

  List<CategoryVo> getAll();

  R pageCategory(CategoryPageDto arg);

  R addCategory(CategoryEditDto arg);

  R echoCategory(Long id);

  R putCategory(CategoryEditDto arg);

  R delCategory(Long id);
}
