package com.lynas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lynas.domain.R;
import com.lynas.domain.dto.MenuQueryDto;
import com.lynas.domain.dto.auth.MenuAddDto;
import com.lynas.domain.entity.Menu;
import com.lynas.domain.vo.RoutesVo;
import com.lynas.domain.vo.admin.MenuVo;

import java.util.List;

/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author LynasTing
 * @since 2024-04-04 19:04:40
 */
public interface MenuService extends IService<Menu> {

  List<String> selectPermsById(Long id);

  List<Menu> selectMenuById(Long id);

  R queryAllMenu(MenuQueryDto arg);

  R addMenu(MenuAddDto arg);

  R echoMenu(Long id);

  R putMenu(MenuAddDto arg);

  R delMenu(Long menuId);
}
