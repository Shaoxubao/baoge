package com.baoge.netty_protocol.server;

import com.baoge.netty_protocol.handle.ServerMessageHandler;
import com.baoge.netty_protocol.message.MessageDecoder;
import com.baoge.netty_protocol.message.MessageEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/26
 *
 * 实现基于自定义协议的心跳功能
 */
public class Server {

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            // 添加用于处理粘包和拆包问题的处理器
                            pipeline.addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4));
                            pipeline.addLast(new LengthFieldPrepender(4));
                            // 添加自定义协议消息的编码和解码处理器
                            pipeline.addLast(new MessageEncoder());
                            pipeline.addLast(new MessageDecoder());
                            // 添加具体的消息处理器
                            pipeline.addLast(new ServerMessageHandler());
                        }
                    });

            ChannelFuture future = bootstrap.bind(8585).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
