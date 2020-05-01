package com.baoge.tomcat.servlet;

import com.baoge.tomcat.http.MyRequest;
import com.baoge.tomcat.http.MyResponse;
import com.baoge.tomcat.http.MyServlet;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/1
 */
public class SecondServlet extends MyServlet {
    @Override
    public void doGet(MyRequest request, MyResponse response) throws Exception {
        this.doPost(request, response);
    }

    @Override
    public void doPost(MyRequest request, MyResponse response) throws Exception {
        response.write("This is the second Servlet.");
    }
}
