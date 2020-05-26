package com.baoge.netty_protocol.common;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/26
 */
public enum MessageTypeEnum {

    REQUEST((byte) 1), RESPONSE((byte) 2), PING((byte) 3), PONG((byte) 4), EMPTY((byte) 5);

    private byte type;

    MessageTypeEnum(byte type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static MessageTypeEnum get(byte type) {
        for (MessageTypeEnum value : values()) {
            if (value.type == type) {
                return value;
            }
        }

        throw new RuntimeException("unsupported type: " + type);
    }
}
