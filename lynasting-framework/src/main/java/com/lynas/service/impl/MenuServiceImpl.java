package com.lynas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lynas.constants.SystemConst;
import com.lynas.domain.R;
import com.lynas.domain.dto.MenuQueryDto;
import com.lynas.domain.dto.auth.MenuAddDto;
import com.lynas.domain.entity.Menu;
import com.lynas.domain.vo.admin.MenuVo;
import com.lynas.enums.AppHttpCodeEnum;
import com.lynas.excepion.SystemException;
import com.lynas.mapper.MenuMapper;
import com.lynas.service.MenuService;
import com.lynas.utils.BeanCopyUtils;
import com.lynas.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author LynasTing
 * @since 2024-04-04 19:04:40
 */
@Service
@Slf4j
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

  /**
   * 查询用户所拥有的权限，要求是所有菜单类型为C或F的、状态正常的、未被删除的
   */
  @Override
  public List<String> selectPermsById(Long id) {
    // 如果用户id为1代表是超级管理员，返回所有可用权限
    if(SecurityUtils.isAdmin()) {
      LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
      // 使用 LambdaQueryWrapper.in方法，可以查多个条件
      wrapper.in(Menu::getMenuType, SystemConst.MENU_TYPE_C, SystemConst.MENU_TYPE_F);
      wrapper.eq(Menu::getStatus, SystemConst.MENU_STATUS_NORMAL);
      List<Menu> list = list(wrapper);
      List<String> menus = list.stream()
        .map(Menu::getPerms)
        .collect(Collectors.toList());
      return menus;
    }
    return getBaseMapper().selectPermsById(id);
  }

  @Override
  public List<Menu> selectMenuById(Long id) {
    List<Menu> menus = null;
    MenuMapper menuMapper = getBaseMapper();
    if (SecurityUtils.isAdmin()) {
      menus = menuMapper.selectAllMenu();
    } else {
      menus = menuMapper.selectMenuById(id);
    }
    return filterToTree(menus, 0L);
  }

  /**
   * 查询所有菜单
   */
  @Override
  public R queryAllMenu(MenuQueryDto arg) {
    LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();

    wrapper.like(StringUtils.hasText(arg.getMenuName()), Menu::getMenuName, arg.getMenuName());
    if(null != arg.getStatus() && arg.getStatus() instanceof Integer) {
      wrapper.eq(Menu::getStatus, arg.getStatus());
    }
    wrapper.orderByAsc(Menu::getOrderNum);
    wrapper.orderByAsc(Menu::getId);
    List<Menu> menus = list(wrapper);
    List<MenuVo> menuVos = BeanCopyUtils.beanListCopy(menus, MenuVo.class);
    return R.okResult(menuVos);
  }

  @Override
  public R addMenu(MenuAddDto arg) {
    if(!StringUtils.hasText(arg.getMenuName())) {
      throw new SystemException(AppHttpCodeEnum.EMAIL_IS_NULL);
    }

    // TODO 其它非空判断和表里是否已存在

    Menu menu = BeanCopyUtils.beanCopy(arg, Menu.class);
    save(menu);
    return R.okResult();
  }

  /**
   * 菜单编辑回显
   */
  @Override
  public R echoMenu(Long id) {
    if(null == id) {
      throw new SystemException(AppHttpCodeEnum.ID_IS_NULL);
    }
    Menu byId = getById(id);
    MenuVo menuVo = BeanCopyUtils.beanCopy(byId, MenuVo.class);
    return R.okResult(menuVo);
  }

  /**
   * 菜单修改
   */
  @Override
  public R putMenu(MenuAddDto arg) {
    // TODO 各种非空判断，跟新增大概一样
    if(Objects.equals(arg.getId(), arg.getParentId())) {
      throw new SystemException(AppHttpCodeEnum.PUBLIC_ERROR);
    }
    Menu menu = BeanCopyUtils.beanCopy(arg, Menu.class);
    getBaseMapper().updateById(menu);
    return R.okResult();
  }

  /**
   * 删除菜单
   */
  @Override
  public R delMenu(Long menuId) {

    LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(Menu::getParentId, menuId);

    // 如果有子菜单
    if(count(wrapper) > 0) {
      throw new SystemException(AppHttpCodeEnum.PUBLIC_ERROR);
    }

    // 判断当前菜单是否存在
    Menu byId = getById(menuId);
    if(null == byId) {
      throw new SystemException(AppHttpCodeEnum.PUBLIC_ERROR);
    }

    getBaseMapper().deleteById(byId);
    return R.okResult();
  }

  /**
   * 一维数组转树形
   */
  private List<Menu> filterToTree(List<Menu> menus, Long pid) {
    List<Menu> collects = menus.stream()
      .filter(item -> item.getParentId().equals(pid))
      .map(item -> item.setChildren(getChildren(item, menus)))
      .collect(Collectors.toList());
    return collects;
  }

  /**
   * 获取当前项的子集
   */
  private List<Menu> getChildren(Menu item, List<Menu> menus) {
    List<Menu> result = menus.stream()
      .filter(sub -> sub.getParentId().equals(item.getId()))
      .map(sub -> sub.setChildren(getChildren(sub, menus)))
      .collect(Collectors.toList());
    return result;
  }
}