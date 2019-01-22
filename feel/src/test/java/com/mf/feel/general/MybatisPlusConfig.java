package com.mf.feel.general;

import lombok.Data;

/**
 * 自动生成所需对象配置参数
 *
 * @author xurunfei
 */
@Data
public class MybatisPlusConfig {

    /**
     * 驱动名称
     */
    private String driverName;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 数据库链接
     */
    private String url;

    /**
     * 需要自动生成的表格
     */
    private String[] tables;
    /**
     * 作者
     */
    private String author;
    /**
     * 输出目录
     */
    private String outputDir;
    private String unixOutputDir;

    /**
     * 大的包目录
     */
    private String parent;
    /**
     * 包目录下controller目录(下同)
     */
    private String controller;
    private String service;
    private String entity;
    private String mapper;
    private String xml;

    //需要消除的表前缀
    private String[] tablePre;
}
