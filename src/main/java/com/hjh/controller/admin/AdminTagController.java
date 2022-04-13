package com.hjh.controller.admin;

import com.hjh.pojo.Tag;
import com.hjh.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by 洪锦辉
 * 2021/8/11 17:47
 */
@Controller
@RequestMapping("/admin")
public class AdminTagController {
    @Autowired
    private TagService tagService;

    @RequestMapping("/tags")
    public String tags(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC)
                                   Pageable pageable, Model model) {
        model.addAttribute("page", tagService.listTags(pageable));
        return "admin/tags";
    }

    @RequestMapping("/tags/input")
    public String input(Tag tag, RedirectAttributes attributes) {
        return "admin/tags-input";
    }

    @RequestMapping("/tags/add")
    public String add(Tag tag, RedirectAttributes attributes) {
        Tag t = tagService.saveTag(tag);
        if (t == null) {
            attributes.addFlashAttribute("message", "添加成功");
        } else {
            attributes.addFlashAttribute("message", "添加失败");
        }
        return "redirect:/admin/tags";
    }

    @RequestMapping("/tags/{id}/update")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tags-update";
    }

    @RequestMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id) {
        tagService.deleteTag(id);
        return "redirect:/admin/tags";
    }
}

