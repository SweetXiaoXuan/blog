package com.mf.feel.dto.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoReqDTO implements Serializable {

    private String username;
    private String password;
}
