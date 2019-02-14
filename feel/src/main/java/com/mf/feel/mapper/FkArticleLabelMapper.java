package com.mf.feel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mf.feel.model.FkArticleLabel;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章和标签关系 Mapper 接口
 * </p>
 *
 * @author xurunfei
 * @since 2019-02-13
 */
@Mapper
public interface FkArticleLabelMapper extends BaseMapper<FkArticleLabel> {

}
