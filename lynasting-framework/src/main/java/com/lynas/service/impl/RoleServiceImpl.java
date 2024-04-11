package com.lynas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lynas.domain.R;
import com.lynas.domain.dto.auth.RolePageDto;
import com.lynas.domain.vo.PageVo;
import com.lynas.domain.vo.admin.RolePageVo;
import com.lynas.mapper.RoleMapper;
import com.lynas.domain.entity.Role;
import com.lynas.service.RoleService;
import com.lynas.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author LynasTing
 * @since 2024-04-06 14:18:04
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

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
}
