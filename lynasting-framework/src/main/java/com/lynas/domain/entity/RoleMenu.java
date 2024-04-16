package com.lynas.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * 角色和菜单关联表(RoleMenu)表实体类
 *
 * @author LynasTing
 * @since 2024-04-16 20:15:02
 */
@TableName("sys_role_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenu implements Serializable {
        /**
         * 角色ID
         */

    private Long roleId;
        /**
         * 菜单ID
         */

    private Long menuId;

}
