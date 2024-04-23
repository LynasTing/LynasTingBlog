package com.lynas.domain.vo.content;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CategoryPageVo {
  @ExcelProperty("分类id")
  private Long id;

  @ExcelProperty("分类名")
  private String name;

  @ExcelProperty("描述")
  private String description;

  @ExcelProperty("状态 1:正常, 0禁用")
  private int status;
}
