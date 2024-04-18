package com.lynas.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * 角色信息表(Role)表实体类
 *
 * @author LynasTing
 * @since 2024-04-06 14:18:03
 */
@TableName("sys_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    // 角色ID
    private Long id;
    // 角色名称
    private String roleName;
    // 角色权限字符串
    private String roleKey;
    // 显示顺序
    private Integer roleSort;
    // 角色状态（1正常 0停用）
    private Integer status;
    // 删除标志（0代表存在 1代表删除）
    @TableLogic
    private Integer delFlag;
    // 创建者
    private Long createBy;
    // 创建时间
    private Date createTime;
    // 更新者
    private Long updateBy;
    // 更新时间
    private Date updateTime;
    // 备注
    private String remark;

}
