package com.lynas.domain.vo.content;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
public class CategoryPageVo {
  @ExcelProperty("分类id")
  private Long id;

  @ExcelProperty("分类名")
  private String name;

  @ExcelProperty("描述")
  private String description;

  @ExcelProperty("状态 1代表未审核，2代表审核通过，3代表审核未通过")
  private Integer status;
}
