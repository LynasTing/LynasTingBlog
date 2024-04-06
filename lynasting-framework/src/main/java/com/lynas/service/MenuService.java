package com.lynas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lynas.domain.entity.Menu;

import java.util.List;

/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author LynasTing
 * @since 2024-04-04 19:04:40
 */
public interface MenuService extends IService<Menu> {

  List<String> selectPermsById(Long id);
}
