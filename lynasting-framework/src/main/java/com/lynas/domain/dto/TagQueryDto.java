package com.lynas.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "标签查询")
public class TagQueryDto {
  /**
   * 标签名
   */
  private String name;

  /**
   * 备注
   */
  private String remark;
}
