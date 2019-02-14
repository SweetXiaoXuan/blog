package com.mf.feel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping
    public String test() {
        return "test";
    }

    /**
     * 页面跳转 demo
     */
    @GetMapping("demo")
    public ModelAndView demo() {
        return new ModelAndView("demo.html");
    }
}
