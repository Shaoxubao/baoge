package com.baoge.netty_protocol.common;

import java.util.UUID;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/26
 */
public class SessionIdGenerator {

    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
