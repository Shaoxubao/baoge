package com.baoge.netty_protocol.resolver;

import com.baoge.netty_protocol.message.Message;
import com.baoge.netty_protocol.common.MessageTypeEnum;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/26
 */
public class PongMessageResolver implements Resolver {

    @Override
    public boolean support(Message message) {
        return message.getMessageType() == MessageTypeEnum.PONG;
    }

    @Override
    public Message resolve(Message message) {
        // 接收到pong消息后，不需要进行处理，直接返回一个空的message
        System.out.println("receive pong message: " + System.currentTimeMillis());
        Message empty = new Message();
        empty.setMessageType(MessageTypeEnum.EMPTY);
        return empty;
    }
}
