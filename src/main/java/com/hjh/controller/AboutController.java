package com.hjh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 洪锦辉
 * 2021/8/15 17:59
 */
@Controller
public class AboutController {

    @RequestMapping("/about")
    public String about() {
        return "about";
    }
}

