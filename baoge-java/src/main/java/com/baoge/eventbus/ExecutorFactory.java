package com.baoge.eventbus;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorFactory {

    public Executor getExecutorService() {
        return new ThreadPoolExecutor(5,
                10,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(16));
    }
}