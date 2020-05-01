package com.baoge.rpc.provider;

import com.baoge.rpc.api.IRpcService;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/1
 */
public class RpcServiceImpl implements IRpcService {
    /**
     * 加
     */
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * 减
     */
    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    /**
     * 乘
     */
    @Override
    public int mult(int a, int b) {
        return a * b;
    }

    /**
     * 除
     */
    @Override
    public int div(int a, int b) {
        return a / b;
    }
}
