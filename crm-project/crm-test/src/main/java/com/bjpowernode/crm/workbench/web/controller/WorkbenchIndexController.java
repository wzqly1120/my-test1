package com.bjpowernode.crm.workbench.web.controller;/*
 *2020/11/9
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.*;

@Controller
public class WorkbenchIndexController {
                    
    @RequestMapping("/workbench/index.do")
    public String index(){
        return "workbench/index";
    }


}
