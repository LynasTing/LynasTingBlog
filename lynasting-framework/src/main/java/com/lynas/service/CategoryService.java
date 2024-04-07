package com.lynas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lynas.domain.R;
import com.lynas.domain.entity.Category;

/**
 * 分类表(Category)表服务接口
 *
 * @author LynasTing
 * @since 2024-03-25 10:43:59
 */
public interface CategoryService extends IService<Category> {

  R getList();

  R getAll();
}
