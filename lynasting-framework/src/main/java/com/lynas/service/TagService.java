package com.lynas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lynas.domain.R;
import com.lynas.domain.entity.Tag;
import com.lynas.domain.dto.TagQueryDto;
import com.lynas.domain.vo.TagVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 标签(Tag)表服务接口
 *
 * @author LynasTing
 * @since 2024-04-04 16:39:28
 */
public interface TagService extends IService<Tag> {
  R getAll(Integer pageNum, Integer pageSize, TagQueryDto arg);

  R addTag(Tag arg);

  R delTag(Long id);

  R<TagVo> echoTag(Long id);

  R editTag(TagVo arg);

  R<TagVo> getAllTag();
}
