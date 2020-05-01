package com.baoge.tomcat.handler;

import com.baoge.tomcat.http.MyRequest;
import com.baoge.tomcat.http.MyResponse;
import com.baoge.tomcat.http.MyServlet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;

import java.util.Map;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/1
 */
public class MyTomcatHandler extends ChannelInboundHandlerAdapter {

    private Map<String, MyServlet> servletMapping;

    public MyTomcatHandler(Map<String, MyServlet> servletMapping) {
        this.servletMapping = servletMapping;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            System.out.println("hello");

            HttpRequest req = (HttpRequest) msg;
            // 转交给我们自己的request实现
            MyRequest myRequest = new MyRequest(ctx, req);
            // 转交给我们自己的response实现
            MyResponse myResponse = new MyResponse(ctx, req);
            // 实际业务处理
            String url = myRequest.getUrl();
            if (servletMapping.containsKey(url)) {
                servletMapping.get(url).service(myRequest, myResponse);
            } else {
                myResponse.write("404 - Not Found");
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }

}
