package com.baoge.netty000.bio.server;

import com.baoge.netty000.bio.ChannelAdapter;
import com.baoge.netty000.bio.ChannelHandler;

import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author shaoxubao
 * @Date 2019/11/6 15:57
 */
public class BioServerHandler extends ChannelAdapter {

    public BioServerHandler(Socket socket, Charset charset) {
        super(socket, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        System.out.println("BioServer链接报告LocalAddress:" + ctx.socket().getLocalAddress());
        ctx.writeAndFlush("hi! baoge BioServer to msg for you \r\n");
    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " BioServer接收到消息：" + msg);
        ctx.writeAndFlush("hi 我BioServer已经收到你的消息Success！\r\n");
    }
}
