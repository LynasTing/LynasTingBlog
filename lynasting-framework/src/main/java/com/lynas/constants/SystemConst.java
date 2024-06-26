package com.lynas.constants;

public class SystemConst {
  /**
   * 已发布
   */
  public static final int ARTICLE_STATUS_NORMAL = 1;

  /**
   * 草稿
   */
  public static final int ARTICLE_STATUS_DRAFT = 0;

  /**
   * 文章分类是正常
   */
  public static final int CATEGORY_STATUS_NORMAL = 1;

  /**
   * 友链审核通过
   */
  public static final int LINK_STATUS_NORMAL = 2;

  /**
   * 文章评论根评论
   */
  public static final int COMMENT_LIST_ROOT = -1;

  /**
   * 文章评论
   */
  public static final String ARTICLE_COMMENT = "1";

  /**
   * 友链评论
   */
  public static final String LINK_COMMENT = "2";

  /**
   * 文章访问量在redis中的key
   */
  public static final String REDIS_VIEW_COUNT_KEY = "article:viewCount";

  /**
   * 后台系统用户token在redis中的key
   */
  public static final String ADMIN_REDIS_TOKEN = "adminlogin";

  /**
   * 菜单类型为C的(菜单)
   */
  public static final String MENU_TYPE_C = "C";

  /**
   * 菜单类型为F的(按钮)
   */
  public static final String MENU_TYPE_F = "F";

  /**
   * 菜单类型正常
   */
  public static final int MENU_STATUS_NORMAL = 1;

  /**
   * 是否是管理员
   */
  public static final int IS_ADMIN = 1;
}
