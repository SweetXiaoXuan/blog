package com.mf.feel.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 文章和标签关系
 * </p>
 *
 * @author xurunfei
 * @since 2019-02-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FkArticleLabel extends Model<FkArticleLabel> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标签id
     */
    private Integer labelId;

    /**
     * 文章id
     */
    private Integer articleId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
