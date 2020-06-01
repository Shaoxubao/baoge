package com.baoge.netty_protocol.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/26
 */
public class Constants {

    /**
     * 魔数:
     * 一个固定的数字，一般用于指定当前字节序列是当前类型的协议，
     * 比如Java生成的class文件起始就使用0xCAFEBABE作为其标识符，对于本服务，这里将其定义为0x1314
     */
    public static int MAGIC_NUMBER = 4;

    // 主版本号
    public static byte MAIN_VERSION = 1;

    // 次版本号
    public static byte SUB_VERSION = 1;

    // 修订版本号
    public static byte MODIFY_VERSION = 1;

    public static int SESSION_ID_LENGTH = 8;


}
