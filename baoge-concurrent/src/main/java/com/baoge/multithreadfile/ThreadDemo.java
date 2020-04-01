package com.baoge.multithreadfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/4/1
 */
public class ThreadDemo {
    public static void main(String[] args) {
        File file = new File("G:" + File.separator + "ThreadDemo.txt");
        try {
            FileOutputStream out = new FileOutputStream(file, true);
            ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
            for (int i = 0; i < 10; i++) {
                new Thread(new MyThread(queue, "线程" + i + ",")).start(); // 多线程往队列中写入数据
                System.out.println("--------------");
            }
            new Thread(new DealFile(out, queue)).start(); // 监听线程，不断从queue中读数据写入到文件中
            try {
                Thread.sleep(3000);
                if (!Thread.currentThread().isAlive()) {
                    System.out.println("线程已结束");
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}

class MyThread implements Runnable {
    private ConcurrentLinkedQueue<String> queue;
    private String contents;

    public MyThread() {
    }

    public MyThread(ConcurrentLinkedQueue<String> queue, String contents) {
        this.queue = queue;
        this.contents = contents;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        queue.add(contents);
    }
}

class DealFile implements Runnable {
    private FileOutputStream out;
    private ConcurrentLinkedQueue<String> queue;

    public DealFile() {
    }

    public DealFile(FileOutputStream out, ConcurrentLinkedQueue<String> queue) {
        this.out = out;
        this.queue = queue;
    }

    @Override
    public void run() {
        synchronized (queue) {
            while (true) {
                if (!queue.isEmpty()) {
                    try {
                        System.out.println("==================");
                        out.write(queue.poll().getBytes("UTF-8"));
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}