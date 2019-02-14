package com.mf.feel.controller;


import com.mf.feel.service.AnnouncementService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * <p>
 * 公告 前端控制器
 * </p>
 *
 * @author xurunfei
 * @since 2019-02-13
 */
@Controller
@RequestMapping("/announcement")
public class AnnouncementController {
    @Resource
    private AnnouncementService announcementService;

}
