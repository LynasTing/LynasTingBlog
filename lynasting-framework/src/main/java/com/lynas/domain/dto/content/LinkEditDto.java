package com.lynas.domain.dto.content;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@NoArgsConstructor
@ApiModel("新增、修改友链")
public class LinkEditDto {
  @ApiModelProperty(notes = "修改传")
  private Long id;

  @NotNull("名称必传")
  @ApiModelProperty(notes = "名称")
  private String name;

  @ApiModelProperty(notes = "logo")
  private String logo;

  @ApiModelProperty(notes = "描述")
  private String description;

  @NotNull("网站地址必传")
  @ApiModelProperty(notes = "网站地址")
  private String address;

  @ApiModelProperty(notes = "审核状态 (1代表未审核，2代表审核通过，3代表审核未通过)")
  private Integer status;
}
