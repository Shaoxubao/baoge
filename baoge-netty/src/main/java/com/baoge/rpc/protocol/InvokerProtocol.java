package com.baoge.rpc.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/1
 *
 * 自定义传输协议
 */

@Data
public class InvokerProtocol implements Serializable {
    private String className;  // 类名
    private String methodName; // 函数名称
    private Class<?>[] params; // 参数类型
    private Object[] values;   // 参数列表

}
