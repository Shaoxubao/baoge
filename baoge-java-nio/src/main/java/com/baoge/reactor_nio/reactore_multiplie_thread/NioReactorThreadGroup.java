package com.baoge.reactor_nio.reactore_multiplie_thread;

import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author shaoxubao
 * @Date 2020/1/10 14:14
 *
 * nio 线程组;简易的NIO线程组
 */
public class NioReactorThreadGroup {
    private static final AtomicInteger requestCounter = new AtomicInteger();  // 请求计数器

    private final int nioThreadCount;  // 线程池IO线程的数量
    private static final int DEFAULT_NIO_THREAD_COUNT;
    private NioReactorThread[] nioThreads;

    static {
        //      DEFAULT_NIO_THREAD_COUNT = Runtime.getRuntime().availableProcessors() > 1
        //              ? 2 * (Runtime.getRuntime().availableProcessors() - 1 ) : 2;

        DEFAULT_NIO_THREAD_COUNT = 4;
    }

    public NioReactorThreadGroup() {
        this(DEFAULT_NIO_THREAD_COUNT);
    }

    public NioReactorThreadGroup(int threadCount) {
        if(threadCount < 1) {
            threadCount = DEFAULT_NIO_THREAD_COUNT;
        }
        this.nioThreadCount = threadCount;
        this.nioThreads = new NioReactorThread[threadCount];
        for(int i = 0; i < threadCount; i ++ ) {
            this.nioThreads[i] = new NioReactorThread();
            this.nioThreads[i].start(); // 构造方法中启动线程，由于nioThreads不会对外暴露，故不会引起线程逃逸
        }

        System.out.println("Nio 线程数量：" + threadCount);
    }

    public void dispatch(SocketChannel socketChannel) {
        if(socketChannel != null ) {
            next().register(socketChannel);
        }
    }

    protected NioReactorThread next() {
        return this.nioThreads[ requestCounter.getAndIncrement() %  nioThreadCount ];
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
}
