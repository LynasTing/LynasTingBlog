package com.lynas.domain.vo.content;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@ApiModel(description = "友链分页查询")
public class LinkPageVo {
  @ApiModelProperty(notes = "id")
  private Long id;

  @ApiModelProperty(notes = "名称")
  private String name;

  @ApiModelProperty(notes = "描述")
  private String description;

  @ApiModelProperty(notes = "网站地址")
  private String address;

  @ApiModelProperty(notes = "审核状态 (1代表未审核，2代表审核通过，3代表审核未通过)")
  private Integer status;
}
