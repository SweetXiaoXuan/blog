package com.blog.api.model;

import lombok.Data;

/**
 * user
 * @author liumengwei
 * @date 2018/10/12
 * @since V1.0
 */
@Data
public class User {
    private Integer id;

    private String username;

    private String password;
}
