package com.baoge.my_impl;

/**
 * @Author shaoxubao
 * @Date 2020/5/18 11:12
 */
public interface MyThreadPoolService {

    void execute(Runnable command);

    void shutdown();

}
