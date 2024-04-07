package com.lynas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lynas.domain.R;
import com.lynas.domain.entity.Tag;
import com.lynas.domain.vo.PageVo;
import com.lynas.domain.dto.TagQueryDto;
import com.lynas.domain.vo.TagVo;
import com.lynas.enums.AppHttpCodeEnum;
import com.lynas.excepion.SystemException;
import com.lynas.mapper.TagMapper;
import com.lynas.service.TagService;
import com.lynas.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 标签(Tag)表服务实现类
 *
 * @author LynasTing
 * @since 2024-04-04 16:39:28
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
  /**
   * 获取所有标签
   * @return
   */
  @Override
  public R getAll(Integer pageNum, Integer pageSize, TagQueryDto arg) {
    LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();

    wrapper.eq(StringUtils.hasText(arg.getName()), Tag::getName,arg.getName());
    wrapper.eq(StringUtils.hasText(arg.getRemark()), Tag::getRemark,arg.getRemark());

    Page<Tag> page = new Page(pageNum, pageSize);
    page(page, wrapper);

    List<TagVo> tagVos = BeanCopyUtils.beanListCopy(page.getRecords(), TagVo.class);
    return R.okResult(new PageVo(tagVos, page.getTotal()));
  }

  /**
   * 新增标签
   */
  @Override
  public R addTag(Tag arg) {
    if(!StringUtils.hasText(arg.getName()))  {
      throw new SystemException(AppHttpCodeEnum.CONTENT_IS_NULL);
    }
    save(arg);
    return R.okResult();
  }

  /**
   * 逻辑删除标签
   */
  @Override
  public R delTag(Long id) {
    Tag byId = getById(id);
    byId.setDelFlag(1);
    getBaseMapper().deleteById(id);
    return R.okResult();
  }

  /**
   * 数据回显
   */
  @Override
  public R<TagVo> echoTag(Long id) {
    Tag byId = getById(id);
    TagVo tagVo = BeanCopyUtils.beanCopy(byId, TagVo.class);
    return R.okResult(tagVo);
  }
}
