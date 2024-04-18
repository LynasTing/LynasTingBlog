package com.lynas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lynas.domain.R;
import com.lynas.domain.dto.auth.RoleAddDto;
import com.lynas.domain.dto.auth.RolePageDto;
import com.lynas.domain.dto.auth.RoleStatusDto;
import com.lynas.domain.entity.RoleMenu;
import com.lynas.domain.vo.PageVo;
import com.lynas.domain.vo.admin.EchoRoleVo;
import com.lynas.domain.vo.admin.RolePageVo;
import com.lynas.enums.AppHttpCodeEnum;
import com.lynas.excepion.SystemException;
import com.lynas.mapper.RoleMapper;
import com.lynas.domain.entity.Role;
import com.lynas.service.RoleMenuService;
import com.lynas.service.RoleService;
import com.lynas.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author LynasTing
 * @since 2024-04-06 14:18:04
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

  @Autowired
  private RoleMenuService roleMenuService;

  @Override
  public List<String> selectRolesById(Long id) {
    return getBaseMapper().selectRolesById(id);
  }

  /**
   * 角色列表分页
   */
  @Override
  public R pageRole(RolePageDto arg) {
    LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
    wrapper.like(StringUtils.hasText(arg.getRoleName()), Role::getRoleName, arg.getRoleName());
    wrapper.eq(!Objects.isNull(arg.getStatus()), Role::getStatus, arg.getStatus());

    wrapper.orderByAsc(Role::getRoleSort);

    Page<Role> page = new Page<>(arg.getPageNum(), arg.getPageSize());
    page(page, wrapper);

    List<RolePageVo> rolePageVos = BeanCopyUtils.beanListCopy(page.getRecords(), RolePageVo.class);
    return R.okResult(new PageVo(rolePageVos, page.getTotal()));
  }

  /**
   * 修改角色状态
   */
  @Override
  public R changeStatus(RoleStatusDto arg) {
    if(Objects.isNull(arg.getRoleId())) {
      throw new SystemException(AppHttpCodeEnum.ID_IS_NULL);
    }
    Role byId = getById(arg.getRoleId());
    byId.setStatus(arg.getStatus());
    getBaseMapper().updateById(byId);
    return R.okResult();
  }

  /**
   * 新增角色
   */
  @Override
  public R addRole(RoleAddDto arg) {
    Role role = BeanCopyUtils.beanCopy(arg, Role.class);
    save(role);

    List<RoleMenu> collect = arg.getMenuIds().stream()
      .map(id -> new RoleMenu(role.getId(), id))
      .collect(Collectors.toList());

    roleMenuService.saveBatch(collect);
    return R.okResult();
  }

  /**
   * 更新角色
   */
  @Override
  public R putRole(RoleAddDto arg) {
    if (Objects.isNull(arg.getId())) {
      throw new SystemException(AppHttpCodeEnum.ID_IS_NULL);
    }
    List<RoleMenu> collect = arg.getMenuIds().stream()
      .map(item -> new RoleMenu(arg.getId(), item))
      .collect(Collectors.toList());
    roleMenuService.remove(Wrappers.<RoleMenu> lambdaQuery().eq(RoleMenu::getRoleId, arg.getId()));
    roleMenuService.saveBatch(collect);
    Role role = BeanCopyUtils.beanCopy(arg, Role.class);
    getBaseMapper().updateById(role);
    return R.okResult();
  }

  /**
   * 删除角色
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public R delRole(Long id) {
    try {
      if (Objects.isNull(id)) {
        throw new SystemException(AppHttpCodeEnum.ID_IS_NULL);
      }
      getBaseMapper().deleteById(id);
      return R.okResult();
    }catch (Exception e) {
      e.printStackTrace();
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      return R.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
    }
  }

  /**
   * 角色回显
   */
  @Override
  public R echoRole(Long id) {
    if(Objects.isNull(id)) {
      throw new SystemException(AppHttpCodeEnum.ID_IS_NULL);
    }
    Role byId = getById(id);
    EchoRoleVo echoRoleVo = BeanCopyUtils.beanCopy(byId, EchoRoleVo.class);
    return R.okResult(echoRoleVo);
  }
}
