package com.baoge.netty002;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @Author shaoxubao
 * @Date 2020/1/14 17:38
 *
 * 通过实现通道适配器ChannelInboundHandlerAdapter.channelRead获取并解析接收数据
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 接收msg消息
        ByteBuf buf = (ByteBuf) msg;
        byte[] msgByte = new byte[buf.readableBytes()];
        buf.readBytes(msgByte);
        System.out.print(new Date() + "接收到消息：");
        System.out.println(new String(msgByte, Charset.forName("UTF-8")));
    }
}
