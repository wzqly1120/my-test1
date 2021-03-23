package com.bjpowernode.crm.workbench.web.controller;/*
 *2020/12/9
 */

import com.bjpowernode.crm.commons.contants.MyContants;
import com.bjpowernode.crm.commons.domain.ReturnObject;

import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.commons.utils.UUIDUtils;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class ActllerController {
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;
    
    @RequestMapping("/workbench/activity/index.do")
    public String index(HttpServletRequest request){
    
        //调用service层
       List<User> userList= userService.queryAllUsers();
       
       //把数据存放到request中
        request.setAttribute("userList", userList);
        
        //转发请求
        return "workbench/activity/index";
    }
    
    
    @RequestMapping("/workbench/activity/saveCreateActivity.do")
    public@ResponseBody Object saveCreateActivity(Activity activity, HttpSession httpSession){
        
        
        User user=(User) httpSession.getAttribute(MyContants.SESSION_USER);
        ReturnObject returnObject=new ReturnObject();

        //封装参数
        activity.setId(UUIDUtils.getUUID());
        activity.setCreateTime(DateUtils.formateDateTime(new Date()));
        activity.setCreateBy(user.getId());
        
        try {
            
        //调用service层 保存创建好的市场活动
        int ret=activityService.saveCreateActivity(activity);//返回的是一个int类型整数
        //判断ret里是否有数据 ret大于1说明创建了一个市场活动
        if (ret>0){
            returnObject.setCode(MyContants.AJAX_RETURN_CODE_SUCCESS); //1
        }else {
            returnObject.setCode(MyContants.AJAX_RETURN_CODE_FAIL); //0
            returnObject.setMessage("系统忙，请稍后重试....");
        }   
            
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(MyContants.AJAX_RETURN_CODE_FAIL); //0
            returnObject.setMessage("系统忙，请稍后重试....");
        }
        return returnObject;
        
        
    }
}
