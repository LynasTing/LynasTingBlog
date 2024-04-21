package com.lynas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lynas.domain.R;
import com.lynas.domain.dto.auth.UserEditVo;
import com.lynas.domain.dto.auth.UserPageDto;
import com.lynas.domain.entity.Role;
import com.lynas.domain.entity.UserRole;
import com.lynas.domain.vo.PageVo;
import com.lynas.domain.vo.UserInfoVo;
import com.lynas.domain.vo.admin.RoleGetVo;
import com.lynas.domain.vo.admin.UserPageVo;
import com.lynas.enums.AppHttpCodeEnum;
import com.lynas.excepion.SystemException;
import com.lynas.mapper.UserMapper;
import com.lynas.domain.entity.User;
import com.lynas.service.RoleService;
import com.lynas.service.UserRoleService;
import com.lynas.service.UserService;
import com.lynas.utils.BeanCopyUtils;
import com.lynas.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户表(User)表服务实现类
 *
 * @author LynasTing
 * @since 2024-03-31 15:54:32
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

  @Autowired
  private UserRoleService userRoleService;
  @Autowired
  private RoleService roleService;
  @Override
  public R getUserInfo() {
    // 拿到id
    Long userId = SecurityUtils.getUserId();
    // 查询出用户信息
    User user = getById(userId);
    // vo封装
    UserInfoVo userInfoVo = BeanCopyUtils.beanCopy(user, UserInfoVo.class);
    // 返回
    return R.okResult(userInfoVo);
  }

  @Override
  public R putUserInfo(User user) {
    updateById(user);
    return R.okResult();
  }

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public R register(User user) {
    // 非空判断
    if(!StringUtils.hasText(user.getUsername())) {
      throw new SystemException(AppHttpCodeEnum.USERNAME_IS_NULL);
    }
    if(!StringUtils.hasText(user.getNickname())) {
      throw new SystemException(AppHttpCodeEnum.NICKNAME_IS_NULL);
    }
    if(!StringUtils.hasText(user.getPassword())) {
      throw new SystemException(AppHttpCodeEnum.PASSWORD_IS_NULL);
    }
    if(!StringUtils.hasText(user.getEmail())) {
      throw new SystemException(AppHttpCodeEnum.EMAIL_IS_NULL);
    }
    // 数据是否已存在
    if(usernameExist(user.getUsername())) {
      throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
    }
    // 密码加密
    String encode = passwordEncoder.encode(user.getPassword());
    user.setPassword(encode);
    // 插入
    save(user);
    return R.okResult();
  }

  /**
   * 分页查询用户列表
   */
  @Override
  public R pageUser(UserPageDto arg) {
    LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
    wrapper.like(StringUtils.hasText(arg.getUsername()), User::getUsername, arg.getUsername());
    if(!Objects.isNull(arg.getStatus())) {
      wrapper.eq(User::getStatus, arg.getStatus());
    }
    if(!Objects.isNull(arg.getPhoneNum())) {
      wrapper.eq(User::getPhoneNum, arg.getPhoneNum());
    }
    Page<User> page = new Page(arg.getPageNum(), arg.getPageSize());
    page(page, wrapper);
    List<UserPageVo> userPageVos = BeanCopyUtils.beanListCopy(page.getRecords(), UserPageVo.class);
    return R.okResult(new PageVo(userPageVos, page.getTotal()));
  }

  /**
   * 新增用户
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public R addUser(UserEditVo arg) {
    try {
      // TODO 各种非空校验
      if(!StringUtils.hasText(arg.getUsername())) {
        throw new SystemException(AppHttpCodeEnum.NICKNAME_IS_NULL);
      }
      // TODO 字段重复校验
      if(usernameExist(arg.getUsername())) {
        throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
      }
      // 密码加密
      String encode = passwordEncoder.encode(arg.getPassword());
      arg.setPassword(encode);

      User user = BeanCopyUtils.beanCopy(arg, User.class);
      save(user);
      // 关联用户角色表
      List<UserRole> collects = arg.getRoleIds().stream()
        .map(item -> new UserRole(user.getId(), item))
        .collect(Collectors.toList());
      userRoleService.saveBatch(collects);
      return R.okResult();
    }catch(SystemException e) {
      return R.errorResult(e.getCode(), e.getMsg());
    }catch (Exception e) {
      e.printStackTrace();
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      return R.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
    }
  }

  /**
   * 删除用户
   */
  @Override
  public R delUser(Long id) {
    if(Objects.isNull(id)) {
      throw new SystemException(AppHttpCodeEnum.ID_IS_NULL);
    }
    User user = getById(id);
    user.setDelFlag(1);
    getBaseMapper().deleteById(user);
    return R.okResult();
  }

  /**
   * 回显用户
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public R echoUser(Long id) {
    try {
      if(Objects.isNull(id)) {
        throw new SystemException(AppHttpCodeEnum.ID_IS_NULL);
      }
      // 用户关联的角色列表
      LambdaQueryWrapper<UserRole> urWrapper = new LambdaQueryWrapper<>();
      urWrapper.eq(UserRole::getUserId, id);
      List<Long> roleIds = userRoleService.list(urWrapper)
        .stream()
        .map(item -> item.getRoleId())
        .collect(Collectors.toList());

      // 所有角色列表
      LambdaQueryWrapper<Role> rWrapper = new LambdaQueryWrapper<>();
      rWrapper.select(Role::getId, Role::getRoleName, Role::getStatus);
      List<Role> list = roleService.list(rWrapper);
      List<RoleGetVo> collects = list
        .stream()
        .map(item -> BeanCopyUtils.beanCopy(item, RoleGetVo.class))
        .collect(Collectors.toList());
      // 用户信息
      User user = getById(id);
      Map<String, Object> map = new HashMap<>();
      map.put("roleIds", roleIds);
      map.put("roles", collects);
      map.put("user", user);
      return R.okResult(map);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    }
  }

  private boolean usernameExist(String username) {
    LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(User::getUsername, username);
    return count(queryWrapper) > 0;
  }
}
