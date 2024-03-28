package com.lynas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lynas.constants.SystemConst;
import com.lynas.domain.ResponseResult;
import com.lynas.domain.entity.Link;
import com.lynas.domain.vo.LinkVo;
import com.lynas.mapper.LinkMapper;
import com.lynas.service.LinkService;
import com.lynas.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author LynasTing
 * @since 2024-03-28 10:33:23
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

  @Override
  public ResponseResult getAllLink() {
    LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Link::getStatus, SystemConst.LINK_STATUS_NORMAL);
    List<Link> list = list(queryWrapper);
    // 查询所有审核通过的
    List<LinkVo> linkVos = BeanCopyUtils.beanListCopy(list, LinkVo.class);
    return ResponseResult.okResult(linkVos);
  }
}
