package com.bjpowernode.crm.commons.domain;/*
 *2020/11/9
 */

public class ReturnObject {
    private String code;
    private String message;
    private Object reData;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getReData() {
        return reData;
    }

    public void setReData(Object reData) {
        this.reData = reData;
    }
}
