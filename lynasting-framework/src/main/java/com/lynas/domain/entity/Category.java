package com.lynas.domain.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * 分类表(Category)表实体类
 *
 * @author LynasTing
 * @since 2024-03-25 10:41:02
 */
@TableName("lt_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {

    private Long id;
    //分类名
    private String name;
    //父分类id，如果没有父分类为-1
    private Long pid;
    //描述
    private String description;
    //状态 1:正常, 0禁用
    private int status;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;

}
