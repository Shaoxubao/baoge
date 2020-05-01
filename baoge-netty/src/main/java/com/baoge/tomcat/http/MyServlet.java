package com.baoge.tomcat.http;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/1
 */
public abstract class MyServlet {

    public void service(MyRequest request, MyResponse response) throws Exception {
        if ("GET".equalsIgnoreCase(request.getMethord())) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    public abstract void doGet(MyRequest request, MyResponse response) throws Exception;
    public abstract void doPost(MyRequest request, MyResponse response) throws Exception;
}
