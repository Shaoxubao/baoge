package com.baoge.tomcat;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/1
 */

import com.baoge.tomcat.http.MyRequest;
import com.baoge.tomcat.http.MyResponse;
import com.baoge.tomcat.http.MyServlet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 简易Netty实现的Tomcat
 * Tomcat 启动类
 */
public class Tomcat {

    private int PORT = 8080;

    private Map<String, MyServlet> servletMapping = new HashMap<>();

    private Properties webXml = new Properties();

    private void init() {
        // 加载web.xml，同时初始化servletMapping
        try {
            String WEB_INF_PATH = this.getClass().getResource("/").getPath();
            FileInputStream fis = new FileInputStream(WEB_INF_PATH + "web.properties");
            webXml.load(fis);

            for (Object keyItem : webXml.keySet()) {
                String key = keyItem.toString();
                if (!key.endsWith(".url")) {
                    continue;
                }
                String servletName = key.replaceAll("\\.url$", "");
                String url = webXml.getProperty(key);
                String className = webXml.getProperty(servletName + ".className");

                MyServlet servlet = (MyServlet) Class.forName(className).newInstance();
                servletMapping.put(url, servlet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        init();

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    // 主线程处理类,看到这样的写法，底层就是用反射
                    .channel(NioServerSocketChannel.class)
                    // 子线程处理类 , Handler
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        // 客户端初始化处理
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            // 无锁化串行编程
                            //Netty对HTTP协议的封装，顺序有要求
                            // HttpResponseEncoder 编码器
                            // 责任链模式，双向链表Inbound OutBound
                            socketChannel.pipeline().addLast(new HttpResponseEncoder());
                            // HttpRequestDecoder 解码器
                            socketChannel.pipeline().addLast(new HttpRequestDecoder());
                            // 业务逻辑处理
                            socketChannel.pipeline().addLast(new MyTomcatHandler());
                        }
                    })
                    // 针对主线程的配置 分配线程最大数量 128)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 针对子线程的配置 保持长连接
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = serverBootstrap.bind(PORT).sync();
            System.out.println("Tomcat Startup, the port is: " + PORT);
            future.channel().closeFuture().sync();
        } catch (Exception e) {

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public class MyTomcatHandler extends ChannelInboundHandlerAdapter {
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

    public static void main(String[] args) {
        new Tomcat().start();
    }

}
