package com.bjpowernode.crm.settings.web.controller;/*
 *2020/11/9
 */

import com.bjpowernode.crm.commons.contants.MyContants;
import com.bjpowernode.crm.commons.domain.ReturnObject;
import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin(){


        return "settings/qx/user/login";

    }

    @RequestMapping("/settings/qx/user/login.do")
    public @ResponseBody  Object login(String loginAct, String loginPwd, String isRemPwd , HttpServletResponse response, HttpSession session, HttpServletRequest request){
        //封装参数
        Map<String,Object>map=new HashMap<>();
        map.put("loginAct", loginAct);
        map.put("loginPwd", loginPwd);

        
        //调用service层方法查询用户
        User user=userService.queryUserByLoginActAndPwd(map);
        ReturnObject returnObject=new ReturnObject();


        //根据查询结果，生成响应信息
        if (user==null){
            //登录失败，账号密码错误
            returnObject.setCode("0");
            returnObject.setMessage("用户名或者密码错误");

        }else {
            //判断账号是否合法。。。


            if (DateUtils.formateDateTime(new Date()).compareTo(user.getExpireTime())>0){
                //账号过期
                returnObject.setCode(MyContants.AJAX_RETURN_CODE_FAIL);
                returnObject.setMessage("账号已过期");
            }else if(MyContants.USER_STATE_LOCKED.equals(user.getLockState())){
                // 状态被锁定
                returnObject.setCode(MyContants.AJAX_RETURN_CODE_FAIL);
                returnObject.setMessage("登录失败，状态被锁定");
            }else if (!user.getAllowIps().contains(request.getRemoteAddr())){
                //ip被锁定
                returnObject.setCode(MyContants.AJAX_RETURN_CODE_FAIL);
                returnObject.setMessage("ip被锁定");

            }else {
                //登录成功
                returnObject.setCode(MyContants.AJAX_RETURN_CODE_SUCCESS);
                //
                session.setAttribute(MyContants.SESSION_USER, user);

                //如果用户选择了记住密码 则以cookie的形式写到浏览器
                if("true".equals(isRemPwd)){
                    //创建记住用户名的cookie
                    Cookie c1=new Cookie("loginAct", loginAct);
                    c1.setMaxAge(10*24*60*60);
                    response.addCookie(c1);
                    //创建记住用户密码的cookie
                    Cookie c2=new Cookie("loginPwd", loginPwd);
                    c2.setMaxAge(10*24*60*60);
                    response.addCookie(c2);
                } else {
                    //如果没选中记住密码 销毁cookie 原理是把之前的cookie覆盖
                    //给新的cookie设置成0秒
                    Cookie c1=new Cookie("loginAct", "1");
                    c1.setMaxAge(0);
                    response.addCookie(c1);
                    Cookie c2=new Cookie("loginPwd", "1");
                    c1.setMaxAge(0);
                    response.addCookie(c2);
                    
                }

            }                       


        }
        return returnObject;
        
    }
    @RequestMapping("/settings/qx/user/logout.do")
    public String logout( HttpServletResponse response,HttpSession session){
        //覆盖cookie
        Cookie c1=new Cookie("loginAct", "1");
        c1.setMaxAge(0);
        response.addCookie(c1);
        Cookie c2=new Cookie("loginPwd", "1");
        c1.setMaxAge(0);
        response.addCookie(c2);

        //销毁session
        session.invalidate();

        return "redirect:/";
    }
}
