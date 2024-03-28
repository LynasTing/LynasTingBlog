package com.lynas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lynas.domain.ResponseResult;
import com.lynas.domain.entity.Link;

/**
 * 友链(Link)表服务接口
 *
 * @author LynasTing
 * @since 2024-03-28 10:33:22
 */
public interface LinkService extends IService<Link> {

  ResponseResult getAllLink();
}
