package com.mf.feel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mf.feel.mapper.ArticleCommentMapper;
import com.mf.feel.model.ArticleComment;
import com.mf.feel.service.ArticleCommentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章评论 服务实现类
 * </p>
 *
 * @author xurunfei
 * @since 2019-02-13
 */
@Service
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements ArticleCommentService {

}
