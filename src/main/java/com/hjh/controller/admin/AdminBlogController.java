package com.hjh.controller.admin;

import com.hjh.pojo.Blog;
import com.hjh.pojo.User;
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

import javax.servlet.http.HttpSession;

/**
 * Created by 洪锦辉
 * 2021/8/10 17:30
 */

@Controller
@RequestMapping("/admin")
public class AdminBlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @RequestMapping("/blogs")
    public String blogs(@PageableDefault(size = 7, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model, Blog blog) {
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        model.addAttribute("types", typeService.listTypes());
        return "admin/blogs";
    }

    @RequestMapping("/blogs/search")
    public String search(@PageableDefault(size = 7, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                Pageable pageable, Model model, Blog blog) {
        //System.out.println(blog);
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "admin/blogs::blogList";
    }

    @RequestMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("types", typeService.listTypes());
        model.addAttribute("tags", tagService.listTags());
        return "admin/blogs-input";
    }

    @RequestMapping("/blogs/add")
    public String add(Blog blog, HttpSession session, String tagIds, Long typeId) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(typeId));
        blog.setTags(tagService.listTags(tagIds));
        blogService.saveBlog(blog);
        return "redirect:/admin/blogs";
    }

    @RequestMapping("/blogs/{id}/update")
    public String update(@PathVariable Long id, Model model) {
        Blog b = blogService.getBlog(id);
        model.addAttribute("blog", b);
        model.addAttribute("types", typeService.listTypes());
        model.addAttribute("tags", tagService.listTags());
        model.addAttribute("tagIds", tagService.getTagsId(b));
        return "admin/blogs-update";
    }

    @RequestMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return "redirect:/admin/blogs";
    }
}

