package com.baoge.netty_protocol.resolver;

import com.baoge.netty_protocol.message.Message;
import com.baoge.netty_protocol.common.MessageTypeEnum;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/26
 */
public class ResponseMessageResolver implements Resolver {

    private static final AtomicInteger counter = new AtomicInteger(1);

    @Override
    public boolean support(Message message) {
        return message.getMessageType() == MessageTypeEnum.RESPONSE;
    }

    @Override
    public Message resolve(Message message) {
        // 接收到对方服务的响应消息之后，对响应消息进行处理，这里主要是将其打印出来
        int index = counter.getAndIncrement();
        System.out.println("[trx: " + message.getSessionId() + "]"
                + index + ". receive response: " + message.getBody());
        System.out.println("[trx: " + message.getSessionId() + "]"
                + index + ". attachments: " + message.getAttachments());

        // 响应消息不需要向对方服务再发送响应，因而这里写入一个空消息
        Message empty = new Message();
        empty.setMessageType(MessageTypeEnum.EMPTY);
        return empty;
    }
}
