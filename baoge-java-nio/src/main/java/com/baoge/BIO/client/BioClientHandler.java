package com.baoge.BIO.client;

import com.baoge.BIO.ChannelAdapter;
import com.baoge.BIO.ChannelHandler;

import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author shaoxubao
 * @Date 2019/11/6 16:13
 */
public class BioClientHandler extends ChannelAdapter {

    public BioClientHandler(Socket socket, Charset charset) {
        super(socket, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        System.out.println("链接报告LocalAddress:" + ctx.socket().getLocalAddress());
        ctx.writeAndFlush("hi! 我是baoge BioClient to msg for you \r\n");
    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " BioClient接收到消息：" + msg);
        ctx.writeAndFlush("hi 我BioClient已经收到你的消息Success！\r\n");
    }
}
