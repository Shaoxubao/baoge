package com.baoge.netty_protocol.factory;

import com.baoge.netty_protocol.message.Message;
import com.baoge.netty_protocol.resolver.Resolver;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/26
 */
public final class MessageResolverFactory {

    // 创建一个工厂类实例
    private static final MessageResolverFactory resolverFactory = new MessageResolverFactory();
    private static final List<Resolver> resolvers = new CopyOnWriteArrayList<>();

    private MessageResolverFactory() {}

    // 使用单例模式实例化当前工厂类实例
    public static MessageResolverFactory getInstance() {
        return resolverFactory;
    }

    public void registerResolver(Resolver resolver) {
        resolvers.add(resolver);
    }

    // 根据解码后的消息，在工厂类处理器中查找可以处理当前消息的处理器
    public Resolver getMessageResolver(Message message) {
        for (Resolver resolver : resolvers) {
            if (resolver.support(message)) {
                return resolver;
            }
        }

        throw new RuntimeException("cannot find resolver, message type: " + message.getMessageType());
    }

}
