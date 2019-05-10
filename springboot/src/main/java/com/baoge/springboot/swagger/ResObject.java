package com.baoge.springboot.swagger;

/**
 * @Author shaoxubao
 * @Date 2019/5/10 11:12
 */
public class ResObject {

    private int value;

    private String msg;

    public ResObject(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
