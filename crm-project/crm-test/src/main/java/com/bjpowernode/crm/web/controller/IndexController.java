package com.bjpowernode.crm.web.controller;/*
 *2020/11/9
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(){
        //直接跳转到首页
       return "index";
    }
                                                                                                          
}
