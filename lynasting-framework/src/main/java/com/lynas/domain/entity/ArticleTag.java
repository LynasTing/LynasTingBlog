package com.lynas.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * 文章标签关联表(ArticleTag)表实体类
 *
 * @author LynasTing
 * @since 2024-04-07 15:25:42
 */
@TableName("lt_article_tag")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleTag implements Serializable {
        /**
         * 文章id
         */

    private Long articleId;
        /**
         * 标签id
         */

    private Long tagId;

}
