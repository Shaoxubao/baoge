package com.baoge.rpc.api;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/1
 */
public interface IRpcService {
    /**
     * 加
     */
    public int add(int a,int b);

    /**
     * 减
     */
    public int sub(int a,int b);

    /**
     * 乘
     */
    public int mult(int a,int b);

    /**
     * 除
     */
    public int div(int a,int b);
}
