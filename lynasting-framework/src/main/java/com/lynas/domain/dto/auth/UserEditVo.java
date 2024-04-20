package com.lynas.domain.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "新增/修改用户")
public class UserEditVo {
  @ApiModelProperty(notes = "id 修改传")
  private Long id;

  @NotNull
  @ApiModelProperty(notes = "用户名")
  private String username;

  @NotNull
  @ApiModelProperty(notes = "昵称")
  private String nickname;

  @NotNull
  @ApiModelProperty(notes = "密码")
  private String password;

  @NotNull
  @ApiModelProperty(notes = "用户类型 ：1代表管理员, 2代表普通用户")
  private int type;

  @NotNull
  @ApiModelProperty(notes = "账号状态 0正常 1停用")
  private int status;

  @ApiModelProperty(notes = "邮箱")
  private String email;

  @NotNull
  @ApiModelProperty(notes = "手机号")
  private String phoneNum;

  @NotNull
  @ApiModelProperty(notes = "用户性别 1男，2女，3未知")
  private int sex;

  @NotNull
  @ApiModelProperty(notes = "角色")
  private List<Long> roleIds;
}
