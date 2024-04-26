package com.lynas.domain.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * 友链(Link)表实体类
 *
 * @author LynasTing
 * @since 2024-03-28 10:33:21
 */
@TableName("lt_link")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Link implements Serializable {

    private Long id;

    private String name;

    private String logo;

    private String description;
    //网站地址
    private String address;
    //审核状态 (1代表未审核，2代表审核通过，3代表审核未通过)
    private Integer status;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;

}
