package com.hjh.controller;

import com.hjh.pojo.Blog;
import com.hjh.pojo.Tag;
import com.hjh.pojo.Type;
import com.hjh.service.BlogService;
import com.hjh.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by 洪锦辉
 * 2021/8/15 16:02
 */
@Controller
public class TagController {
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;

    @RequestMapping("/tags/{id}")
    public String BlogsByTagId(@PageableDefault(size = 6, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                        Pageable pageable, @PathVariable Long id, Model model) {
        List<Tag> tags = tagService.listTagTop(10000);
        Blog blog = new Blog();
        if (id == -1) {
            id = tags.get(0).getId();
        }
        Type t = new Type(); t.setId(id);
        blog.setType(t);
        model.addAttribute("tags", tags);
        model.addAttribute("page", blogService.listBlog(pageable, id));
        model.addAttribute("activeId", id);
        return "tags";
    }
}

