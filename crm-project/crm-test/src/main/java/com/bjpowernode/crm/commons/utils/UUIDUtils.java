package com.bjpowernode.crm.commons.utils;/*
 *2020/12/10
 */

import java.util.UUID;

public class UUIDUtils {
    //获取UUID 就是生成一个随机不重复的32位字符串
    
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
