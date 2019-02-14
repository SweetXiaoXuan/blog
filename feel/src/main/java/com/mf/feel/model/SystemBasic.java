package com.mf.feel.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 系统配置
 * </p>
 *
 * @author xurunfei
 * @since 2019-02-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SystemBasic extends Model<SystemBasic> {

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    private String title;

    /**
     * 副标题
     */
    private String subheading;

    /**
     * 备案号
     */
    private String recordNumber;

    /**
     * 登陆超时设置
     */
    private String loginTimeoutSettings;

    /**
     * 站点url
     */
    private String url;

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 描述
     */
    private String description;

    /**
     * 邮箱
     */
    private String email;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
