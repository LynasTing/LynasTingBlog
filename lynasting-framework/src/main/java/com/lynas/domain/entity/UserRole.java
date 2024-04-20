package com.lynas.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * 用户和角色关联表(UserRole)表实体类
 *
 * @author LynasTing
 * @since 2024-04-20 18:07:16
 */
@TableName("sys_user_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole implements Serializable {
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 角色ID
     */
    private Long roleId;
}
