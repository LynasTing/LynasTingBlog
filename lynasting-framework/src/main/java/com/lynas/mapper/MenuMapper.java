package com.lynas.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lynas.domain.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author LynasTing
 * @since 2024-04-04 19:04:39
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

  List<String> selectPermsById(Long id);

  List<Menu> selectAllMenu();

  List<Menu> selectMenuById(Long id);
}
