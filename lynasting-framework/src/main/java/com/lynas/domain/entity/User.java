package com.lynas.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * 用户表(User)表实体类
 *
 * @author LynasTing
 * @since 2024-03-28 15:36:56
 */
@TableName("sys_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    //主键
    private Long id;
    //用户名
    private String username;
    //昵称
    private String nickname;
    //密码
    private String password;
    //用户类型：1代表管理员, 2代表普通用户
    private int type;
    //账号状态（0正常 1停用）
    private int status;
    //邮箱
    private String email;
    //手机号
    private String phoneNum;
    //用户性别（1男，2女，3未知）
    private int sex;
    //头像
    private String avatar;
    //创建人的用户id
    private Long createBy;
    //创建时间
    private Date createTime;
    //更新人
    private Long updateBy;
    //更新时间
    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    @TableLogic
    private Integer delFlag;

}
