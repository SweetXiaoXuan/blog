package com.mf.feel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mf.feel.model.ArticleComment;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章评论 Mapper 接口
 * </p>
 *
 * @author xurunfei
 * @since 2019-02-13
 */
@Mapper
public interface ArticleCommentMapper extends BaseMapper<ArticleComment> {

}
