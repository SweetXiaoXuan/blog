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
 * 用户信息表
 * </p>
 *
 * @author xurunfei
 * @since 2019-02-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserInfo extends Model<UserInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private LocalDateTime loginTime;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 真实姓名
     */
    private String actualName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 旧密码
     */
    private String oldPassword;

    /**
     * 文章数
     */
    private String articlesNum;

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
