package com.liuqs.springbootfreemarker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ Author: liuqianshun
 * @ Description:页面请求转发
 * @ Date: Created in 2017-12-15
 * @ Modified:
 **/
@Controller
@RequestMapping(value = "/page")
public class PageController {

    @RequestMapping(value = "/index")
    public String index(ModelMap model){
        model.addAttribute("name","liuqs");
        model.addAttribute("date","2017-12-15 16:59:11");
        return "index";
    }
}
