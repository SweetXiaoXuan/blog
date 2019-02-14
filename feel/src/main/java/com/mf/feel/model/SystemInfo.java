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
 * 系统信息
 * </p>
 *
 * @author xurunfei
 * @since 2019-02-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SystemInfo extends Model<SystemInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 服务器软件
     */
    private String serverSoftware;

    /**
     * java版本
     */
    private String javaVersion;

    /**
     * 操作系统
     */
    private String operatingSystem;

    /**
     * java运行方式
     */
    private String javaMode;

    /**
     * mysql版本
     */
    private String mysqlVersion;

    /**
     * 程序版本
     */
    private String programVersion;

    /**
     * 程序编码
     */
    private String programCode;

    /**
     * 版权所有
     */
    private String copyright;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
