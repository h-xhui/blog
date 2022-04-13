package com.hjh.controller;


import com.hjh.pojo.Blog;
import com.hjh.service.BlogService;
import com.hjh.service.TagService;
import com.hjh.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 洪锦辉
 * 2021/8/14 15:38
 */
@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @RequestMapping("/")
    public String index(@PageableDefault(size = 7, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTagTop(10));
        model.addAttribute("recommendBlogs", blogService.listRecommendTopBlog(4));
        return "index";
    }

    @RequestMapping("/blogs/{id}")
    public String blogContent(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.MarkdownToHtml(id));
        return "blog";
    }
}

