package com.lynas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lynas.constants.SystemConst;
import com.lynas.domain.R;
import com.lynas.domain.dto.content.CategoryPageDto;
import com.lynas.domain.dto.content.LinkEditDto;
import com.lynas.domain.entity.Link;
import com.lynas.domain.vo.LinkVo;
import com.lynas.domain.vo.PageVo;
import com.lynas.domain.vo.content.LinkPageVo;
import com.lynas.enums.AppHttpCodeEnum;
import com.lynas.mapper.LinkMapper;
import com.lynas.service.LinkService;
import com.lynas.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * 友链(Link)表服务实现类
 *
 * @author LynasTing
 * @since 2024-03-28 10:33:23
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

  @Override
  public R getAllLink() {
    LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Link::getStatus, SystemConst.LINK_STATUS_NORMAL);
    List<Link> list = list(queryWrapper);
    // 查询所有审核通过的
    List<LinkVo> linkVos = BeanCopyUtils.beanListCopy(list, LinkVo.class);
    return R.okResult(linkVos);
  }

  /**
   * 分页查询友联
   */
  @Transactional(rollbackFor = Exception.class)
  @Override
  public R pageLink(CategoryPageDto args) {
    try {
      LambdaQueryWrapper<Link> wrapper = new LambdaQueryWrapper<>();
      wrapper.select(Link::getName, Link::getStatus, Link::getAddress, Link::getId, Link::getDescription);
      if (!Objects.isNull(args.getStatus())) {
        wrapper.eq(Link::getStatus, args.getStatus());
      }
      wrapper.like(StringUtils.hasText(args.getName()), Link::getName, args.getName());
      Page<Link> page = new Page(args.getPageNum(), args.getPageSize());
      page(page, wrapper);
      return R.okResult(new PageVo(BeanCopyUtils.beanListCopy(page.getRecords(), LinkPageVo.class), page.getTotal()));
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      return R.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
    }
  }

  /**
   * 新增友链
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public R addLink(LinkEditDto args) {
    try {
      if(!StringUtils.hasText(args.getName()) || !StringUtils.hasText(args.getAddress())) {
        return R.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
      }
      Link link = BeanCopyUtils.beanCopy(args, Link.class);
      save(link);
      return R.okResult();
    }catch (RuntimeException e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      return R.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
    }
  }

  /**
   * 更新友链
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public R putLink(LinkEditDto args) {
    try {
      if(Objects.isNull(args.getId())) {
        return R.errorResult(AppHttpCodeEnum.ID_IS_NULL);
      }
      if(!StringUtils.hasText(args.getName()) || !StringUtils.hasText(args.getAddress())) {
        return R.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
      }
      Link link = BeanCopyUtils.beanCopy(args, Link.class);
      updateById(link);
      return R.okResult();
    }catch (RuntimeException e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      return R.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
    }
  }
}
