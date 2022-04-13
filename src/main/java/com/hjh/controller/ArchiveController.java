package com.hjh.controller;


import com.hjh.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 洪锦辉
 * 2021/8/15 16:54
 */
@Controller
public class ArchiveController {
    @Autowired
    private BlogService blogService;

    @RequestMapping("/archive")
    public String archive(Model model) {
        model.addAttribute("archiveBlog", blogService.archiveBlog());
        model.addAttribute("blogCount", blogService.blogCount());
        return "archives";
    }
}

