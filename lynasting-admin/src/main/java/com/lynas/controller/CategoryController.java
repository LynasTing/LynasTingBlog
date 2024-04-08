package com.lynas.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.lynas.domain.R;
import com.lynas.domain.entity.Category;
import com.lynas.domain.vo.CategoryVo;
import com.lynas.domain.vo.ExcelCategoryVo;
import com.lynas.enums.AppHttpCodeEnum;
import com.lynas.service.CategoryService;
import com.lynas.utils.BeanCopyUtils;
import com.lynas.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/content/category")
public class CategoryController {
  @Autowired
  private CategoryService categoryService;

  /**
   * 查询所有分类
   */
  @GetMapping("/listAllCategory")
  public R getAll() {
    List<CategoryVo> list = categoryService.getAll();
    return R.okResult(list);
  }

  /**
   * 导出所有分类
   */
  @GetMapping("/export")
  public void export(HttpServletResponse res) {
    try {
      // 设置下载的文件的请求头
      WebUtils.setDownLoadHeader("分类.xlsx", res);

      // 获取需要导出的数据
      List<Category> categories = categoryService.list();
      List<ExcelCategoryVo> vos = BeanCopyUtils.beanListCopy(categories, ExcelCategoryVo.class);

      // 将数组写入到Excel中
      EasyExcel.write(res.getOutputStream(), ExcelCategoryVo.class)
        .autoCloseStream(Boolean.FALSE)
        .sheet("分类导出")
        .doWrite(vos);

    } catch (Exception e) {
      // 出现异常相应JSON
      R r = R.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
      WebUtils.renderString(res, JSON.toJSONString(r));
    }
  }
}
