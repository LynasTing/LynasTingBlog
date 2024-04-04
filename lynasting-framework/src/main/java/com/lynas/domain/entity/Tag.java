package com.lynas.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * 标签(Tag)表实体类
 *
 * @author LynasTing
 * @since 2024-04-04 16:39:27
 */
@TableName("lt_tag")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag implements Serializable {

    @TableId
    private Long id;
    //标签名
    private String name;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;
    //备注
    private String remark;

}
