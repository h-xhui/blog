package com.hjh.controller.admin;

import com.hjh.pojo.User;
import com.hjh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;

/**
 * Created by 洪锦辉
 * 2021/8/10 15:10
 */
@Controller
@RequestMapping("/admin")
public class AdminIndexController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public String loginPage() {
        return "admin/login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session, RedirectAttributes attributes) {
        User user = userService.checkUser(username, password==null?null:DigestUtils.md5DigestAsHex(password.getBytes()));
        if (user != null) {
            //user.setPassword(null);
            session.setAttribute("user", user);
            return "admin/index";
        } else {
            attributes.addFlashAttribute("message", "用户名或密码错误");
            return "redirect:/admin";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}

