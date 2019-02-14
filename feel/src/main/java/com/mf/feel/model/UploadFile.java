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
 * 上传文件表
 * </p>
 *
 * @author xurunfei
 * @since 2019-02-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UploadFile extends Model<UploadFile> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件大小
     */
    private Integer fileSize;

    /**
     * 文件类型
     */
    private Integer fileType;

    /**
     * 上传人
     */
    private String uid;

    /**
     * 文件存储地址
     */
    private String fileUrl;

    /**
     * 排序值
     */
    private Integer sort;

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
