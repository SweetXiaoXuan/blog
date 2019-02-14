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
 * 栏目
 * </p>
 *
 * @author xurunfei
 * @since 2019-02-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Programa extends Model<Programa> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 栏目名称
     */
    private String columnName;

    /**
     * 栏目别名
     */
    private String columnAlias;

    /**
     * 父节点
     */
    private Integer parentId;

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private Integer state;

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
