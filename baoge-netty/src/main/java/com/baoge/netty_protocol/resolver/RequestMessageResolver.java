package com.baoge.netty_protocol.resolver;

import com.baoge.netty_protocol.message.Message;
import com.baoge.netty_protocol.common.MessageTypeEnum;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/26
 */
public class RequestMessageResolver implements Resolver {

    private static final AtomicInteger counter = new AtomicInteger(1);

    @Override
    public boolean support(Message message) {
        return message.getMessageType() == MessageTypeEnum.REQUEST;
    }

    @Override
    public Message resolve(Message message) {
        // 接收到request消息之后，对消息进行处理，这里主要是将其打印出来
        int index = counter.getAndIncrement();
        System.out.println("[trx: " + message.getSessionId() + "]"
                + index + ". receive request: " + message.getBody());
        System.out.println("[trx: " + message.getSessionId() + "]"
                + index + ". attachments: " + message.getAttachments());

        // 处理完成后，生成一个响应消息返回
        Message response = new Message();
        response.setMessageType(MessageTypeEnum.RESPONSE);
        response.setBody("nice to meet you too!");
        response.addAttachment("name", "xufeng");
        response.addAttachment("hometown", "wuhan");
        return response;
    }

}
