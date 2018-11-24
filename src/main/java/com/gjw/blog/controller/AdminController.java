package com.gjw.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 后台管理控制器.
 *
 * @author gjw
 * @since 1.0.0 2018年11月24日
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    /**
     * 获取后台管理主页面
     * @param model
     * @return
     */
    @GetMapping
    public ModelAndView listUsers(Model model) {
        return new ModelAndView("admin/index", "menuList", model);
    }

}
