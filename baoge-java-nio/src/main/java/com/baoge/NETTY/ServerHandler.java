package com.baoge.NETTY;

import com.baoge.utils.Calculator;
import com.baoge.utils.DigitUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.UnsupportedEncodingException;

/**
 * @Author shaoxubao
 * @Date 2019/11/7 11:41
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 收到客户端消息
     * @throws UnsupportedEncodingException
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        ByteBuf in = (ByteBuf) msg;
        byte[] req = new byte[in.readableBytes()];
        in.readBytes(req);
        String body = new String(req,"utf-8");
        System.out.println("收到客户端消息:" + body);
        String calResult = null;
        try {
            if (DigitUtil.hasDigit(body)) {
                calResult = Calculator.cal(body).toString();
            } else {
                calResult = body;
            }
        } catch(Exception e) {
            calResult = "错误的表达式：" + e.getMessage();
        }
        ctx.write(Unpooled.copiedBuffer(calResult.getBytes()));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * 异常处理
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
