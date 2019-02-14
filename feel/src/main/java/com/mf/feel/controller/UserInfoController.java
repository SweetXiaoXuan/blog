package com.mf.feel.controller;


import com.mf.feel.dto.req.UserInfoReqDTO;
import com.mf.feel.model.UserInfo;
import com.mf.feel.service.UserInfoService;
import com.mf.feel.utils.JsonResult;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author xurunfei
 * @since 2019-02-13
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;

    @RequestMapping(value = "/1.1/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResult get() {
        return new JsonResult(true);
    }

    @RequestMapping(value = "/1.1/user", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResult<UserInfo> put(@RequestBody UserInfoReqDTO userInfoReqDTO) {

        return new JsonResult(true);
    }
}
