package com.mf.feel.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 公告
 * </p>
 *
 * @author xurunfei
 * @since 2019-02-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Announcement extends Model<Announcement> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 详细内容
     */
    private String content;

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 描述
     */
    private String description;

    /**
     * 发布状态 0未发布 1.已发布
     */
    private Integer releaseState;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /**
     * 删除 0 false 未删， 1 true 已删
     */
    private Integer del;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
