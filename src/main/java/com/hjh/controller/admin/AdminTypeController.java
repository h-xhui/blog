package com.hjh.controller.admin;

import com.hjh.pojo.Type;
import com.hjh.service.TypeService;
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
 * 2021/8/11 15:05
 */
@Controller
@RequestMapping("/admin")
public class AdminTypeController {
    @Autowired
    private TypeService typeService;

    @RequestMapping("/types")
    public String types(@PageableDefault(size=10, sort = {"id"},direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model) {
        model.addAttribute("page", typeService.listTypes(pageable));
        return "admin/types";
    }

    @RequestMapping("/types/input")
    public String input() {
        return "admin/types-input";
    }

    @RequestMapping("/types/add")
    public String save(Type type, RedirectAttributes attributes, Model model) {
        Type t = typeService.saveType(type);
        if (t == null) {
            attributes.addFlashAttribute("message", "操作成功");
        } else {
            attributes.addFlashAttribute("message", "操作失败");
        }
        return "redirect:/admin/types";
    }

    @RequestMapping("/types/{id}/update")
    public String update(@PathVariable Long id, Model model) {
        Type t = typeService.getType(id);
        model.addAttribute("type", t);
        return "admin/types-update";
    }

    @RequestMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id) {
        typeService.deleteType(id);
        return "redirect:/admin/types";
    }
}

