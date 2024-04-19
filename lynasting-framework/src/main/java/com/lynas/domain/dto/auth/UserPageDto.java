package com.lynas.domain.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "分页查询用户")
public class UserPageDto {
  @NotNull
  private Long pageNum;

  @NotNull
  private Long pageSize;

  @ApiModelProperty(notes = "用户名称")
  private String username;

  @ApiModelProperty(notes = "用户手机号")
  private Long phoneNum;

  @ApiModelProperty(notes = "用户状态 1正常 0禁用")
  private Integer status;

}
