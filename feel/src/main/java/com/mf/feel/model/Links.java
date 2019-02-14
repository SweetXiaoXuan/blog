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
 * 友情链接
 * </p>
 *
 * @author xurunfei
 * @since 2019-02-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Links extends Model<Links> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 创建人id
     */
    private Integer uid;

    /**
     * 名称
     */
    private String linkName;

    /**
     * 连接url
     */
    private String linkUrl;

    /**
     * 图片url
     */
    private String picUrl;

    /**
     * 描述
     */
    private String description;

    /**
     * 目标 0._blank新窗口 1._self 2._top
     */
    private Integer target;

    /**
     * 是否跟踪访问记录0.nofllow 1.none
     */
    private Integer rel;

    /**
     * 发布状态 0未增加 1.已增加
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
